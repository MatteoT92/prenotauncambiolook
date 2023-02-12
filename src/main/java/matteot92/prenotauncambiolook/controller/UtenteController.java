package matteot92.prenotauncambiolook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import matteot92.prenotauncambiolook.model.entities.Utente;
import matteot92.prenotauncambiolook.model.service.OrdineService;
import matteot92.prenotauncambiolook.model.service.ServizioService;
import matteot92.prenotauncambiolook.model.service.UtenteService;

@RestController
@SessionAttributes({"username", "utenti"})
@CrossOrigin(origins = "http://localhost:4200")
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
	 * Metodo che presi in input i dati del form
	 * verifica se l'utente è registrato
	 * e reindirizza i dati dell'utente trovato al component Angular
	 */
	@PostMapping("/login")
	@CrossOrigin(origins = "http://localhost:4200/login")
	public String login(@RequestBody Utente utenteDaForm) {
		ObjectMapper mapper = new ObjectMapper();
		Utente utente = null;
		if (utenteService.isRegistrato(utenteDaForm)) { // verifica se l'utente è già registrato
			utente = utenteService.cercaUtente(utenteDaForm.getUsername(), utenteDaForm.getEmail(), utenteDaForm.getPassword());
		}
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        String json = null;
		try {
			json = mapper.writeValueAsString(utente);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
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
	 * Metodo che effettua la registrazione di un utente
	 * memorizzando i dati su database
	 * e reindirizzandolo sulla pagina per il login
	 */
	@PostMapping("/sign")
	@CrossOrigin(origins = "http://localhost:4200/sign")
	public String registraUtente(@RequestBody Utente utenteDaForm) {
		ObjectMapper mapper = new ObjectMapper();
		utenteService.registraUtente(utenteDaForm);
		Utente utente = utenteService.cercaUtente(utenteDaForm.getUsername(), utenteDaForm.getEmail());
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
        String json = null;
		try {
			json = mapper.writeValueAsString(utente);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}

	/**
	 * Metodo che effettua la modifica della password dell'utente in sessione
	 * salvandone la modifica su database
	 * e reindirizzando l'utente sulla home page
	 */
	@PostMapping("/password")
	public String modificaPassword(@ModelAttribute("utente") Utente utente, Model model) {
		Utente utenteCercato = utenteService.cercaUtente(utente.getUsername(), utente.getEmail(), utente.getPassword());
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
	
	// UTENTE ADMIN

	/**
	 * Metodo che effettua la modifica della password dell'utente admin in sessione
	 * salvandone la modifica su database
	 * e reindirizzando l'utente sulla pagina del pannello di controllo
	 */
	@PostMapping("/admin/password")
	public String modificaPasswordAdmin(@ModelAttribute("utente") Utente utente, Model model) {
		Utente utenteCercato = utenteService.cercaUtente(utente.getUsername(), utente.getEmail(), utente.getPassword());
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
	
}
