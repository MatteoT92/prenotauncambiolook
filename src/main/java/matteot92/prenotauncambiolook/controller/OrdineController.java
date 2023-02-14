package matteot92.prenotauncambiolook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
	 * tutti i suoi ordini effettuati
	 */
	@RequestMapping(value = "/ordini", method = {RequestMethod.GET, RequestMethod.POST})
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
	@PostMapping("/ordine")
	public String effettuaOrdine(@ModelAttribute("ordine") Ordine ordine, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		Utente utente = utenteService.cercaUtenteDaUsername(username);
		String descrizione = (String) session.getAttribute("descrizione");
		String prezzo = (String) session.getAttribute("prezzo");
		Servizio servizio = servizioService.cercaServizioPerDescrizionePrezzo(descrizione, Double.valueOf(prezzo));
		if (ordineService.prenotazioniPerGiornata(ordine.getData(), ordine.getOrario()) < 3) { // viene verificato che per quella data e orario non ci siano già più di 3 prenotazioni
			ordineService.salvaOrdine(ordine.getData(), ordine.getOrario(), ordine.getQuantita(), utente, servizio);
		}
		return "servizi";
	}
	
	/**
	 * Metodo che consente ad un utente cliente di poter rimuovere un ordine
	 * da lui precedentemente effettuato.
	 * Il cliente verrà reindirizzato sulla pagina dei suoi ordini
	 */
	@PostMapping("/rimuovi-ordine")
	@CrossOrigin(origins = "http://localhost:4200/rimuovi-ordine")
	public String rimuoviOrdine(@RequestBody Ordine ordine) {
		ordine = ordineService.cercaOrdine(ordine.getId());
		ordineService.rimuoviOrdine(ordine);
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
	
	// Metodo che reindirizza alla pagina per effettuare il pagamento di un ordine
	@GetMapping("/pagamento")
	public String pagamento() {
		return "pagamento";
	}
	
	// Metodo che effettua il pagamento di un ordine e reindirizza alla pagina dei servizi disponibili
	@PostMapping("/pagamento")
	public String effettuaPagamento(@ModelAttribute("ordine") Ordine ordine) {
		return "redirect:/servizi";
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
