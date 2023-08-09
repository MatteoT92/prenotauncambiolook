package matteot92.prenotauncambiolook.controller;

import static matteot92.prenotauncambiolook.Json.parseJson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import matteot92.prenotauncambiolook.model.entities.Servizio;
import matteot92.prenotauncambiolook.model.service.ServizioService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ServizioController {

	private ServizioService servizioService;
	
	@Autowired
	public ServizioController(ServizioService servizioService) {
		this.servizioService = servizioService;
	}

	/**
	 * Metodo che mostra ad un utente cliente tutti i servizi offerti dal salone
	 */
	@GetMapping("/servizi")
	@CrossOrigin(origins = "http://localhost:4200/servizi")
	public String serviziDisponibili() {
		return parseJson(servizioService.serviziOfferti());
	}

	/**
	 * Metodo che permette ad un admin di poter aggiungere nuovi servizi compilando il form
	 * e il nuovo servizio verrà salvato sul database.
	 */
	@PostMapping("/servizi")
	@CrossOrigin(origins = "http://localhost:4200/servizi")
	public String aggiungiServizio(@RequestBody Servizio servizio) { // il servizio ricevuto dal form Angular
		return parseJson(servizioService.aggiungiServizio(servizio));
	}
	
}
