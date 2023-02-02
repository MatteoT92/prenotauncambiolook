package matteot92.prenotauncambiolook.controller;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import matteot92.prenotauncambiolook.model.entities.Ordine;
import matteot92.prenotauncambiolook.model.entities.Servizio;
import matteot92.prenotauncambiolook.model.entities.Utente;
import matteot92.prenotauncambiolook.model.service.OrdineService;
import matteot92.prenotauncambiolook.model.service.ServizioService;
import matteot92.prenotauncambiolook.model.service.UtenteService;

@Controller
@SessionAttributes({"username", "utenti", "ordini", "timestamp"})
public class UtenteController {

	private UtenteService utenteService;
	private ServizioService servizioService;
	private OrdineService ordineService;

	@Autowired
	public UtenteController(UtenteService utenteService, ServizioService servizioService, OrdineService ordineService) {
		this.utenteService = utenteService;
		this.servizioService = servizioService;
		this.ordineService = ordineService;
	}

	@GetMapping("/")
	public String indexGet() {
		return "index";
	}

	@PostMapping("/")
	public String indexPost() {
		return "index";
	}

	@GetMapping("/login")
	public String loginGet() {
		return "login";
	}
	
	@GetMapping("/home")
	public String homeGet() {
		return "home";
	}

	@PostMapping("/home")
	public String homePost() {
		return "home";
	}

	@PostMapping("/login")
	public String loginPost(@ModelAttribute("utente") Utente utente, Model model) {
		String view = "redirect:/home";
		if (utenteService.isRegistrato(utente)) { // verifica se l'utente è già registrato
			if (utenteService.isAdmin(utente)) { // poi controlla se ha il ruolo di amministratore
				model.addAttribute("username", utente.getUsername());
				view = "redirect:/admin/pannello";
			} else {
				model.addAttribute("username", utente.getUsername());
			}
		} else {
			view = "redirect:/sign";
		}
		return view;
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		session = request.getSession();
		return "index";
	}

	@GetMapping("/sign")
	public String sign() {
		return "sign";
	}

	@PostMapping("/sign")
	public String registraUtente(@ModelAttribute("utente") Utente utente, Model model) {
		utenteService.registraUtente(utente);
		model.addAttribute("username", utente.getUsername());
		return "redirect:/login";
	}

	@GetMapping("/password")
	public String password() {
		return "password";
	}

	@PostMapping("/password")
	public String modificaPassword(@ModelAttribute("utente") Utente utente, Model model) {
		Utente utenteCercato = utenteService.cercaUtente(utente.getUsername(), utente.getEmail());
		if (utenteCercato != null) {
			utenteService.modificaPassword(utenteCercato, utente.getPassword());
			model.addAttribute("username", utente.getUsername());
		}
		return "redirect:/home";
	}

	@GetMapping("/admin/password")
	public String passwordAdmin() {
		return "admin/password";
	}

	@PostMapping("/admin/password")
	public String modificaPasswordAdmin(@ModelAttribute("utente") Utente utente, Model model) {
		Utente utenteCercato = utenteService.cercaUtente(utente.getUsername(), utente.getEmail());
		if (utenteCercato != null && utenteService.isAdmin(utenteCercato)) {
			utenteService.modificaPassword(utenteCercato, utente.getPassword());
			model.addAttribute("username", utente.getUsername());
		}
		return "redirect:/admin/pannello";
	}

	@GetMapping("/admin/utenti")
	public List<Utente> utentiRegistrati(Model model) {
		model.addAttribute("utenti", utenteService.utentiRegistrati());
		return utenteService.utentiRegistrati();
	}

	@GetMapping("/admin/pannello")
	public String pannelloAmministratore() {
		return "admin/pannello";
	}
	
	@GetMapping("/ordini")
	public String iMieiOrdini(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		Utente utente = utenteService.cercaUtenteDaUsername(username);
		model.addAttribute("ordini", utente.getOrdini());
		return "ordini";
	}
	
	@GetMapping("/cancellati")
	public String disiscriviti(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		Utente utente = utenteService.cercaUtenteDaUsername(username);
		utenteService.rimuoviUtente(utente);
		return "index";
	}
	
	@PostMapping("/ordina")
	public String effettuaOrdine(@ModelAttribute("ordine") Ordine ordine, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		Utente utente = utenteService.cercaUtenteDaUsername(username);
		String descrizione = (String) session.getAttribute("descrizione");
		String prezzo = (String) session.getAttribute("prezzo");
		Servizio servizio = servizioService.cercaServizioPerDescrizionePrezzo(descrizione, Double.valueOf(prezzo));
		//ordineService.salvaOrdine(data, orario, quantita, utente, servizio);
		return "ordini";
	}
}
