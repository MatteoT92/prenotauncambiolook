package matteot92.prenotauncambiolook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

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
	
	// UTENTE CLIENTE
	
	/**
	 * Metodo che mostra ad un utente cliente tutti i servizi offerti dal salone
	 */
	@RequestMapping(value = "/servizi", method = {RequestMethod.GET, RequestMethod.POST})
	@CrossOrigin(origins = "http://localhost:4200/servizi")
	public String serviziDisponibili() {
		ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        String json = null;
		try {
			json = mapper.writeValueAsString(servizioService.serviziOfferti());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}
	
	// UTENTE ADMIN
	
	/**
	 * Metodo che permette ad un admin di poter aggiungere nuovi servizi compilando il form
	 * e il nuovo servizio verrà salvato sul database.
	 * L'admin verrà poi reindirizzato sulla pagina del pannello di controllo
	 */
	@PostMapping("/admin/servizi")
	public String aggiungiServizio(@ModelAttribute("servizio") Servizio servizio) {
		servizioService.aggiungiServizio(servizio);
		return "redirect:/admin/pannello";
	}
	
}
