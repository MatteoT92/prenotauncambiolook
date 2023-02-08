package matteot92.prenotauncambiolook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import matteot92.prenotauncambiolook.model.entities.Utente;
import matteot92.prenotauncambiolook.model.service.OrdineService;
import matteot92.prenotauncambiolook.model.service.ServizioService;
import matteot92.prenotauncambiolook.model.service.UtenteService;

@Controller
@SessionAttributes({"username", "utenti"})
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
	
	// UTENTE CLIENTE

	/**
	 * Metodo che reindirizza sulla pagina index
	 */
	@RequestMapping(value="/", method = {RequestMethod.GET, RequestMethod.POST})
	public String index() {
		return "index";
	}

	/**
	 * Metodo che reindirizza sulla pagina login
	 */
	@GetMapping("/login")
	public String loginGet() {
		return "login";
	}
	
	/**
	 * Metodo che presi in input i dati del form
	 * verifica se l'utente è registrato
	 * poi verifica se è un utente cliente o admin
	 * e reindirizza sulla home page corrispondente allo status
	 */
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
			view = "redirect:/sign"; // reindirizzamento alla pagina per registrarsi per un utente nuovo
		}
		return view;
	}
	
	/**
	 * Metodo che reindirizza sulla pagina home per un utente cliente
	 */
	@RequestMapping(value="/home", method={RequestMethod.GET, RequestMethod.POST})
	public String home() {
		return "home";
	}
	
	/**
	 * Metodo che effettua il logout dell'utente
	 * e cancella la sessione ricreandone una nuova
	 */
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		session = request.getSession();
		return "index";
	}

	/**
	 * Metodo che reindirizza sulla pagina per registrarsi
	 */
	@GetMapping("/sign")
	public String sign() {
		return "sign";
	}

	/**
	 * Metodo che effettua la registrazione di un utente
	 * memorizzando i dati su database
	 * e reindirizzandolo sulla pagina per il login
	 */
	@PostMapping("/sign")
	public String registraUtente(@ModelAttribute("utente") Utente utente, Model model) {
		utenteService.registraUtente(utente);
		model.addAttribute("username", utente.getUsername());
		return "redirect:/login";
	}

	/**
	 * Metodo che reindirizza sulla pagina per modificare la password
	 */
	@GetMapping("/password")
	public String password() {
		return "password";
	}

	/**
	 * Metodo che effettua la modifica della password dell'utente in sessione
	 * salvandone la modifica su database
	 * e reindirizzando l'utente sulla home page
	 */
	@PostMapping("/password")
	public String modificaPassword(@ModelAttribute("utente") Utente utente, Model model) {
		Utente utenteCercato = utenteService.cercaUtente(utente.getUsername(), utente.getEmail());
		if (utenteCercato != null) {
			utenteService.modificaPassword(utenteCercato, utente.getPassword());
			model.addAttribute("username", utente.getUsername());
		}
		return "redirect:/home";
	}
	
	/**
	 * Metodo che consente ad un utente in sessione di potersi disiscrivere dal sito
	 * rimuovendone i dati dal database
	 * e reindirizzandolo sulla pagina index
	 */
	@GetMapping("/cancellati")
	public String disiscriviti(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		Utente utente = utenteService.cercaUtenteDaUsername(username);
		utenteService.rimuoviUtente(utente);
		return "index";
	}
	
	/**
	 * Metodo che reindirizza un utente sulla pagina della chet
	 * gestita tramite WebSocket
	 */
	@RequestMapping("/chat")
	public String chat() {
		return "chat";
	}
	
	// UTENTE ADMIN

	/**
	 * Metodo che reindirizza sulla pagina per modificare la password di un utente admin
	 */
	@GetMapping("/admin/password")
	public String passwordAdmin() {
		return "admin/password";
	}

	/**
	 * Metodo che effettua la modifica della password dell'utente admin in sessione
	 * salvandone la modifica su database
	 * e reindirizzando l'utente sulla pagina del pannello di controllo
	 */
	@PostMapping("/admin/password")
	public String modificaPasswordAdmin(@ModelAttribute("utente") Utente utente, Model model) {
		Utente utenteCercato = utenteService.cercaUtente(utente.getUsername(), utente.getEmail());
		if (utenteCercato != null && utenteService.isAdmin(utenteCercato)) {
			utenteService.modificaPassword(utenteCercato, utente.getPassword());
			model.addAttribute("username", utente.getUsername());
		}
		return "redirect:/admin/pannello";
	}

	/**
	 * Metodo che mostra per l'utente admin tutti gli utenti cliente registrati
	 */
	@GetMapping("/admin/clienti")
	public List<Utente> utentiRegistrati(Model model) {
		model.addAttribute("utenti", utenteService.utentiRegistrati());
		return utenteService.utentiRegistrati();
	}

	/**
	 * Metodo che reindirizza l'utente admin sulla pagina pannello di controllo
	 */
	@RequestMapping(value="/admin/pannello", method={RequestMethod.GET, RequestMethod.POST})
	public String pannelloAdmin() {
		return "admin/pannello";
	}
	
	/**
	 * Metodo che reindirizza un utente admin sulla pagina della chet
	 * gestita tramite WebSocket
	 */
	@RequestMapping("/admin/chat")
	public String chatAdmin() {
		return "admin/chat";
	}
	
}
