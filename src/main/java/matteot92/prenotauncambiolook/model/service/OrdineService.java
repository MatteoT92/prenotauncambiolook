package matteot92.prenotauncambiolook.model.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
	
	/**
	 * Metodo che consente di ritornare una lista di ordini
	 * effettuati dai clienti registrati sul sito del salone
	 */
	public List<Ordine> listaOrdini() {
		return (List<Ordine>) repository.findAll();
	}
	
	/**
	 * Metodo che consente di recuperare un ordine in base al suo codice
	 */
	public Ordine cercaOrdine(Long codice) {
		return repository.findById(codice).get();
	}
	
	/**
	 * Metodo che consente di salvare sul database un ordine effettuato
	 */
	public Ordine salvaOrdine(LocalDate data, LocalTime orario, Integer quantita, Utente utente, Servizio servizio) {
		Ordine ordine = new Ordine();
		ordine.setData(data);
		ordine.setOrario(orario);
		ordine.setQuantita(quantita);
		ordine.setUtente(utente);
		ordine.setServizio(servizio);
		return repository.save(ordine);
	}
	
	/**
	 * Metodo che consente di modificare sul database un ordine effettuato
	 * cambiandone la data e orario
	 */
	public Ordine modificaAppuntamentoOrdine(Ordine ordine, LocalDate data, LocalTime orario) {
		ordine.setData(data);
		ordine.setOrario(orario);
		return repository.save(ordine);
	}
	
	/**
	 * Metodo che consente di modificare sul database un ordine effettuato
	 * cambiandone il servizio scelto precedentemente
	 */
	public Ordine modificaServizioOrdine(Ordine ordine, Servizio servizio) {
		ordine.setServizio(servizio);
		return repository.save(ordine);
	}
	
	/**
	 * Metodo che consente di modificare sul database un ordine effettuato
	 * cambiandone la data, l'orario e il servizio scelto precedentemente
	 */
	public Ordine modificaOrdine(Ordine ordine, LocalDate data, LocalTime orario, Servizio servizio) {
		ordine.setData(data);
		ordine.setOrario(orario);
		ordine.setServizio(servizio);
		return repository.save(ordine);
	}
	
	/**
	 * Metodo che consente di rimuovere dal database un ordine già effettuato
	 */
	public void rimuoviOrdine(Ordine ordine) {
		repository.delete(ordine);
	}

	/**
	 * Metodo che ritorna una lista di ordini effettuati dai clienti
	 * in base alla data selezionata
	 */
	public List<Ordine> ordiniGiornata(LocalDate data) {
		return repository.findByData(data);
	}
	
	/**
	 * Metodo che ritorna una lista di ordini effettuati dai clienti
	 * in base alla data e orario selezionati
	 */
	public List<Ordine> ordiniGiornata(LocalDate data, LocalTime orario) {
		return repository.findByDataAndOrario(data, orario);
	}
	
	/**
	 * Metodo che ritorna una lista di ordini effettuati dai clienti
	 * in base alla data selezionata.
	 * Viene utilizzata una query custom definita nel repository
	 */
	public Integer prenotazioniPerGiornata(LocalDate data) {
		return repository.getPrenotazioniByData(data);
	}
	
	/**
	 * Metodo che ritorna una lista di ordini effettuati dai clienti
	 * in base alla data e orario selezionati.
	 * Viene utilizzata una query custom definita nel repository
	 */
	public Integer prenotazioniPerGiornata(LocalDate data, LocalTime orario) {
		return repository.getPrenotazioniByDataAndOrario(data, orario);
	}
	
	/**
	 * Metodo che ritorna una lista di ordini effettuati da un cliente specifico
	 */
	public List<Ordine> prenotazioniPerCliente(Utente cliente) {
		return repository.findByUtente(cliente);
	}

}
