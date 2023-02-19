package matteot92.prenotauncambiolook.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import matteot92.prenotauncambiolook.model.entities.Ordine;
import matteot92.prenotauncambiolook.model.entities.Servizio;
import matteot92.prenotauncambiolook.model.entities.Utente;
import matteot92.prenotauncambiolook.model.service.OrdineService;
import matteot92.prenotauncambiolook.model.service.ServizioService;
import matteot92.prenotauncambiolook.model.service.UtenteService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class OrdineController {

	private OrdineService ordineService;
	private UtenteService utenteService;
	private ServizioService servizioService;
	
	@Autowired
	public OrdineController(OrdineService ordineService, UtenteService utenteService, ServizioService servizioService) {
		this.ordineService = ordineService;
		this.utenteService = utenteService;
		this.servizioService = servizioService;
	}

	/**
	 * Metodo che mostra per l'utente cliente in sessione
	 * tutti i suoi ordini da lui effettuati
	 */
	@GetMapping("/ordini")
	@CrossOrigin(origins = "http://localhost:4200/ordini")
	public String iMieiOrdini(@RequestParam(name = "username") String username) {
		Utente utente = utenteService.cercaUtenteDaUsername(username);
		ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.registerModule(new JavaTimeModule()); // serializza la data con Jackson JSON
        String json = null;
		try {
			json = mapper.writeValueAsString(ordineService.prenotazioniPerCliente(utente));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}
	
	/**
	 * Metodo che consente all'utente cliente in sessione
	 * di effettuare un ordine, scegliendo fra i servizi disponibili,
	 * inserendo data con orario e quantità.
	 * L'ordine verrà poi salvato sul database solo se per quella data e orario
	 * non vi sono già 3 prenotazioni effettuate (siccome vi sono solo 3 parrucchieri disponibili).
	 * L'utente verrà reindirizzato sulla pagina dei servizi offerti dal salone
	 */
	@PostMapping("/ordini")
	@CrossOrigin(origins = "http://localhost:4200/ordini")
	public String effettuaOrdine(@RequestBody Ordine ordine) {
		Utente utente = utenteService.cercaUtenteDaId(ordine.getUtente());
		Servizio servizio = servizioService.cercaServizio(ordine.getServizio());
		LocalDateTime oggi = LocalDateTime.now();
		LocalDateTime prenotazione = LocalDateTime.of(ordine.getData(), ordine.getOrario());
		if (oggi.compareTo(prenotazione) <= 0) { // verifica che la data della prenotazione non sia precedente ad oggi
			if (ordineService.prenotazioniPerGiornata(ordine.getData(), ordine.getOrario()) < 3) { // viene verificato che per quella data e orario non ci siano già più di 3 prenotazioni
				ordineService.salvaOrdine(ordine.getData(), ordine.getOrario(), ordine.getQuantita(), utente, servizio);
			}
		}
		ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.registerModule(new JavaTimeModule()); // serializza la data con Jackson JSON
        String json = null;
		try {
			json = mapper.writeValueAsString(ordine);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}
	
	/**
	 * Metodo che consente ad un utente cliente di poter rimuovere un ordine
	 * da lui precedentemente effettuato.
	 * Il cliente verrà reindirizzato sulla pagina dei suoi ordini
	 */
	@DeleteMapping("/ordini/{id}")
	@CrossOrigin(origins = "http://localhost:4200/ordini")
	public String rimuoviOrdine(@PathVariable(value = "id") Long id) {
		Ordine ordine = ordineService.cercaOrdine(id);
		LocalDateTime oggi = LocalDateTime.now();
		LocalDateTime prenotazione = LocalDateTime.of(ordine.getData(), ordine.getOrario());
		if (oggi.compareTo(prenotazione) <= 0) { // verifica che la data della prenotazione non sia già passata
			ordineService.rimuoviOrdine(ordine); // se deve ancora avvenire permette al cliente di disdire appuntamento
		}
		ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.registerModule(new JavaTimeModule()); // serializza la data con Jackson JSON
        String json = null;
		try {
			json = mapper.writeValueAsString(ordine);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}
	
	/**
	 * Metodo che consente ad un utente cliente di poter modificare un ordine
	 * da lui precedentemente effettuato.
	 */
	@PatchMapping("/ordini/{id}")
	@CrossOrigin(origins = "http://localhost:4200/ordini/:id")
	public String modificaOrdine(@PathVariable(value = "id") Long id, @RequestBody Ordine ordine) {
		Ordine vecchioOrdine = ordineService.cercaOrdine(id);
		LocalDateTime oggi = LocalDateTime.now();
		LocalDateTime prenotazione = LocalDateTime.of(vecchioOrdine.getData(), vecchioOrdine.getOrario());
		if (oggi.compareTo(prenotazione) <= 0) { // verifica che la data della prenotazione non sia già passata
			if (LocalDateTime.of(ordine.getData(), ordine.getOrario()).compareTo(oggi) >= 0 && // viene verificato che la nuova data e orario non siano antecedenti ad oggi
				ordineService.prenotazioniPerGiornata(ordine.getData(), ordine.getOrario()) < 3) { // viene verificato che per quella data e orario non ci siano già più di 3 prenotazioni
				ordineService.modificaAppuntamentoOrdine(vecchioOrdine, ordine.getData(), ordine.getOrario()); // se deve ancora avvenire permette al cliente di modificare l'ordine
			}
		}
		ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.registerModule(new JavaTimeModule()); // serializza la data con Jackson JSON
        String json = null;
		try {
			json = mapper.writeValueAsString(vecchioOrdine);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}
	
	// Metodo che effettua il pagamento di un ordine
	@PostMapping("/ordine/{idOrdine}/pagamento")
	@CrossOrigin(origins = "http://localhost:4200/ordini/:id/pagamento")
	public String pagamento(@RequestParam(name = "idOrdine") Long id, @RequestBody Ordine ordine) {
		System.err.print(id);
		ordine = ordineService.cercaOrdine(ordine.getId());
		// Inserire poi la logica del pagamento quando verrà creata entità adatta
		ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.registerModule(new JavaTimeModule()); // serializza la data con Jackson JSON
        String json = null;
		try {
			json = mapper.writeValueAsString(ordine);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		System.err.print(json);
		return json;
	}
	
	/**
	 * Metodo che mostra ad un utente admin la lista degli ordini
	 * effettuati da tutti i suoi clienti
	 */
	@RequestMapping(value = "/prenotazioni", method = {RequestMethod.GET, RequestMethod.POST})
	@CrossOrigin(origins = "http://localhost:4200/ordini")
	public String listaPrenotazioni() {
		ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.registerModule(new JavaTimeModule()); // serializza la data con Jackson JSON
        String json = null;
		try {
			json = mapper.writeValueAsString(ordineService.listaOrdini());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}
	
}
