package matteot92.prenotauncambiolook.controller;

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
@SessionAttributes({"appuntamenti", "ordini"})
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
	
	// UTENTE CLIENTE
	
	/**
	 * Metodo che mostra per l'utente cliente in sessione
	 * tutti i suoi ordini effettuati
	 */
	@GetMapping("/ordini")
	public String iMieiOrdini(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		Utente utente = utenteService.cercaUtenteDaUsername(username);
		model.addAttribute("ordini", ordineService.prenotazioniPerCliente(utente));
		return "ordini";
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
	public String rimuoviOrdine(@ModelAttribute("ordine") Ordine ordine) {
		ordineService.rimuoviOrdine(ordine);
		return "redirect:/ordini";
	}

	// UTENTE ADMIN
	
	/**
	 * Metodo che mostra ad un utente admin la lista degli ordini
	 * effettuati da tutti i suoi clienti
	 */
	@GetMapping("/admin/ordini")
	public List<Ordine> listaPrenotazioni(Model model) {
		model.addAttribute("appuntamenti", ordineService.listaOrdini());
		return ordineService.listaOrdini();
	}
	
}
