package matteot92.prenotauncambiolook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import matteot92.prenotauncambiolook.model.entities.Utente;
import matteot92.prenotauncambiolook.model.service.UtenteService;

@Controller
public class UtenteController {

	private UtenteService utenteService;
	
	@Autowired
	public UtenteController(UtenteService utenteService) {
		this.utenteService = utenteService;
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
	
	@PostMapping("/login")
	public String loginPost(@ModelAttribute("utente") Utente utente, Model model) {
		String view = "index";
		if (utenteService.isRegistrato(utente)) { // verifica se l'utente è già registrato
			if (utenteService.isAdmin(utente)) { // poi controlla se ha il ruolo di amministratore
				model.addAttribute("username", utente.getUsername());
				view = "redirect:/admin/pannello";
			} else {
				model.addAttribute("msg", "Login effettuato con successo");
				model.addAttribute("username", utente.getUsername());
			}
		} else {
			model.addAttribute("msg", "Non sei registrato! Effettua prima la registrazione");
			view = "redirect:/sign";
		}
		return view;
	}
	
	@GetMapping("/sign")
	public String sign() {
		return "sign";
	}
	
	@PostMapping("/sign")
	public String registraUtente(@ModelAttribute("utente") Utente utente, Model model) {
		utenteService.registraUtente(utente);
		model.addAttribute("msg", "Ciao " + utente.getUsername());
		model.addAttribute("username", utente.getUsername());
		return "index";
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
		return "redirect:/login";
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
}
