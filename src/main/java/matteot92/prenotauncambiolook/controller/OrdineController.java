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
	
	@GetMapping("/admin/ordini")
	public List<Ordine> listaPrenotazioni(Model model) {
		model.addAttribute("appuntamenti", ordineService.listaOrdini());
		return ordineService.listaOrdini();
	}
	
	@GetMapping("/ordini")
	public String iMieiOrdini(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		Utente utente = utenteService.cercaUtenteDaUsername(username);
		model.addAttribute("ordini", ordineService.prenotazioniPerCliente(utente));
		return "ordini";
	}
	
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
	
	@PostMapping("/rimuovi-ordine")
	public String rimuoviOrdine(@ModelAttribute("ordine") Ordine ordine) {
		ordineService.rimuoviOrdine(ordine);
		return "redirect:/ordini";
	}

}
