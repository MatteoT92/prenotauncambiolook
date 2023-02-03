package matteot92.prenotauncambiolook.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import matteot92.prenotauncambiolook.model.entities.Ordine;
import matteot92.prenotauncambiolook.model.service.OrdineService;

@Controller
@SessionAttributes({"appuntamenti"})
public class OrdineController {

	private OrdineService ordineService;
	
	@Autowired
	public OrdineController(OrdineService ordineService) {
		this.ordineService = ordineService;
	}
	
	@GetMapping("/admin/ordini")
	public List<Ordine> listaPrenotazioni(Model model) {
		model.addAttribute("appuntamenti", ordineService.listaOrdini());
		return ordineService.listaOrdini();
	}
	
	@GetMapping("/query/{data}")
	@ResponseBody
	public Integer test(@PathVariable LocalDate data) {
		return ordineService.prenotazioniPerGiornata(data);
	}
	
	@GetMapping("/query/{data}/{orario}")
	@ResponseBody
	public Integer test(@PathVariable LocalDate data, @PathVariable LocalTime orario) {
		return ordineService.prenotazioniPerGiornata(data, orario);
	}

}
