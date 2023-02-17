package matteot92.prenotauncambiolook.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import matteot92.prenotauncambiolook.model.entities.Servizio;
import matteot92.prenotauncambiolook.model.repository.ServizioRepository;

@Service
public class ServizioService {
	
	private ServizioRepository repository;
	
	@Autowired
	public ServizioService(ServizioRepository repository) {
		this.repository = repository;
	}
	
	/**
	 * Metodo che ritorna una lista dei servizi offerti dal salone
	 * e memorizzati sul database
	 */
	public List<Servizio> serviziOfferti() {
		return (List<Servizio>) repository.findAll();
	}
	
	/**
	 * Metodo che consente di modificare il prezzo di un servizio offerto
	 * presente già sul database
	 */
	public Servizio modificaPrezzo(Servizio servizio, Double nuovoPrezzo) {
		servizio.setPrezzo(nuovoPrezzo);
		return repository.save(servizio);
	}
	
	/**
	 * Metodo che consente di modificare la descrizione di un servizio offerto
	 * presente già sul database
	 */
	public Servizio modificaDescrizione(Servizio servizio, String nuovaDescrizione) {
		servizio.setDescrizione(nuovaDescrizione);
		return repository.save(servizio);
	}
	
	/**
	 * Metodo che consente di modificare la descrizione e il prezzo di un servizio offerto
	 * presente già sul database
	 */
	public Servizio modificaDescrizionePrezzo(Servizio servizio, String nuovaDescrizione, Double nuovoPrezzo) {
		servizio.setDescrizione(nuovaDescrizione);
		servizio.setPrezzo(nuovoPrezzo);
		return repository.save(servizio);
	}
	
	/**
	 * Metodo che consente di aggiungere un nuovo servizio al database
	 */
	public Servizio aggiungiServizio(Servizio servizio) {
		return repository.save(servizio);
	}
	
	/**
	 * Metodo che consente di rimuovere un servizio dal database
	 */
	public void rimuoviServizio(Servizio servizio) {
		repository.delete(servizio);
	}
	
	/**
	 * Metodo che consente di recuperare un servizio dal database
	 * in base al suo codice
	 */
	public Servizio cercaServizio(Long codice) {
		return repository.findById(codice).get();
	}
	
	/**
	 * Metodo che consente di recuperare un servizio dal database
	 * in base alla sua descrizione
	 */
	public Servizio cercaServizioPerDescrizione(String descrizione) {
		return repository.findByDescrizione(descrizione);
	}
	
	/**
	 * Metodo che consente di recuperare dei servizi dal database
	 * in base al prezzo
	 */
	public List<Servizio> cercaServizioPerPrezzo(Double prezzo) {
		return repository.findByPrezzo(prezzo);
	}
	
	/**
	 * Metodo che consente di recuperare un servizio dal database
	 * in base alla sua descrizione e al suo prezzo
	 */
	public Servizio cercaServizioPerDescrizionePrezzo(String descrizione, Double prezzo) {
		return repository.findByDescrizioneAndPrezzo(descrizione, prezzo);
	}

}
