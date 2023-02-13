package matteot92.prenotauncambiolook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
	 * e reindirizza l'utente sulla home del sito come utente registrato
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
	@CrossOrigin(origins = "http://localhost:4200/password")
	public String modificaPassword(@RequestBody Utente utente) {
		Utente utenteCercato = utenteService.cercaUtenteDaUsername(utente.getUsername());
		if (utenteCercato != null) {
			utenteService.modificaPassword(utenteCercato, utente.getPassword());
		}
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
        String json = null;
		try {
			json = mapper.writeValueAsString(utenteCercato);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}
	
	/**
	 * Metodo che consente ad un utente in sessione di potersi disiscrivere dal sito
	 * rimuovendone i dati dal database
	 * e reindirizzandolo sulla pagina home
	 */
	@PostMapping("/disiscriviti")
	@CrossOrigin(origins = "http://localhost:4200/disiscriviti")
	public String disiscriviti(@RequestBody Utente utenteDaRimuovere) {
		Utente utente = utenteService.cercaUtenteDaUsername(utenteDaRimuovere.getUsername());
		utenteService.rimuoviUtente(utente);
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
        String json = null;
		try {
			json = mapper.writeValueAsString(utente);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
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
