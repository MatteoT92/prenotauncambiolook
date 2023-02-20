package matteot92.prenotauncambiolook.controller;

import static matteot92.prenotauncambiolook.Json.parseJson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

	/**
	 * Metodo che presi in input i dati del form
	 * verifica se l'utente è registrato
	 * e reindirizza l'utente sulla home del sito come utente registrato
	 */
	@PostMapping("/login")
	@CrossOrigin(origins = "http://localhost:4200/login")
	public String login(@RequestBody Utente utenteDaForm) {
		Utente utente = null;
		if (utenteService.isRegistrato(utenteDaForm)) { // verifica se l'utente è già registrato
			utente = utenteService.cercaUtente(utenteDaForm.getUsername(), utenteDaForm.getEmail(), utenteDaForm.getPassword());
		}
		return parseJson(utente);
	}

	/**
	 * Metodo che effettua la registrazione di un utente
	 * memorizzando i dati su database
	 * e reindirizzandolo sulla pagina per il login
	 */
	@PostMapping("/sign")
	@CrossOrigin(origins = "http://localhost:4200/sign")
	public String registraUtente(@RequestBody Utente utenteDaForm) {
		utenteService.registraUtente(utenteDaForm);
		Utente utente = utenteService.cercaUtente(utenteDaForm.getUsername(), utenteDaForm.getEmail());
		return parseJson(utente);
	}

	/**
	 * Metodo che effettua la modifica della password dell'utente in sessione
	 * salvandone la modifica su database
	 * e reindirizzando l'utente sulla home del sito
	 */
	@PostMapping("/password")
	@CrossOrigin(origins = "http://localhost:4200/password")
	public String modificaPassword(@RequestBody Utente utente) {
		Utente utenteCercato = utenteService.cercaUtenteDaUsername(utente.getUsername());
		if (utenteCercato != null) {
			utenteService.modificaPassword(utenteCercato, utente.getPassword());
		}
		return parseJson(utenteCercato);
	}
	
	/**
	 * Metodo che consente ad un utente in sessione di potersi disiscrivere dal sito
	 * rimuovendone i dati dal database e reindirizzandolo sulla pagina di login
	 */
	@PostMapping("/disiscriviti")
	@CrossOrigin(origins = "http://localhost:4200/disiscriviti")
	public String disiscriviti(@RequestBody Utente utenteDaRimuovere) {
		Utente utente = utenteService.cercaUtenteDaUsername(utenteDaRimuovere.getUsername());
		utenteService.rimuoviUtente(utente);
		return parseJson(utente);
	}
	
	/**
	 * Metodo che mostra per l'utente admin tutti gli utenti cliente registrati
	 */
	@RequestMapping(value = "/clienti", method = {RequestMethod.GET, RequestMethod.POST})
	@CrossOrigin(origins = "http://localhost:4200/clienti")
	public String utentiRegistrati() {
		return parseJson(utenteService.utentiRegistrati());
	}
	
	/**
	 * Metodo che effettua il recupero della password attraverso l'username e password
	 * forniti nel form Angular
	 */
	@GetMapping("/password")
	@CrossOrigin(origins = "http://localhost:4200/password")
	public String recuperaPassword(@RequestParam(name = "username") String username, @RequestParam(name = "email") String email) {
		Utente utenteCercato = utenteService.cercaUtente(username, email);
		if (utenteCercato != null) {
			utenteService.recuperaPassword(username, email);
		}
		return parseJson(utenteService.recuperaPassword(username, email));
	}
	
}
