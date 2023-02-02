package matteot92.prenotauncambiolook.model.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import matteot92.prenotauncambiolook.model.entities.Ordine;
import matteot92.prenotauncambiolook.model.entities.Servizio;
import matteot92.prenotauncambiolook.model.entities.Utente;
import matteot92.prenotauncambiolook.model.repository.OrdineRepository;

@Service
public class OrdineService {
	
	private OrdineRepository repository;
	
	@Autowired
	public OrdineService(OrdineRepository repository) {
		this.repository = repository;
	}
	
	public List<Ordine> listaOrdini() {
		return (List<Ordine>) repository.findAll();
	}
	
	public Ordine cercaOrdine(Long codice) {
		return repository.findById(codice).get();
	}
	
	public Ordine salvaOrdine(LocalDate data, LocalTime orario, Integer quantita, Utente utente, Servizio servizio) {
		Ordine ordine = new Ordine();
		ordine.setData(data);
		ordine.setOrario(orario);
		ordine.setQuantita(quantita);
		ordine.setUtente(utente);
		ordine.setServizio(servizio);
		return repository.save(ordine);
	}
	
	public Ordine modificaAppuntamentoOrdine(Ordine ordine, LocalDate data, LocalTime orario) {
		ordine.setData(data);
		ordine.setOrario(orario);
		return repository.save(ordine);
	}
	
	public Ordine modificaServizioOrdine(Ordine ordine, Servizio servizio) {
		ordine.setServizio(servizio);
		return repository.save(ordine);
	}
	
	public Ordine modificaOrdine(Ordine ordine, LocalDate data, LocalTime orario, Servizio servizio) {
		ordine.setData(data);
		ordine.setOrario(orario);
		ordine.setServizio(servizio);
		return repository.save(ordine);
	}
	
	public void rimuoviOrdine(Ordine ordine) {
		repository.delete(ordine);
	}

	public List<Ordine> ordiniGiornata(LocalDate data) {
		return repository.findByData(data);
	}
	
	public List<Ordine> ordiniGiornata(LocalDate data, LocalTime orario) {
		return repository.findByDataAndOrario(data, orario);
	}
	
	public Integer prenotazioniPerGiornata(LocalDate data) {
		return repository.getPrenotazioniByData(data);
	}
	
	public Integer prenotazioniPerGiornata(LocalDate data, LocalTime orario) {
		return repository.getPrenotazioniByDataAndOrario(data, orario);
	}

}
