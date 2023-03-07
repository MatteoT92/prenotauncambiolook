package matteot92.prenotauncambiolook.model.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
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
		Servizio servizioConModifiche = null;
		Boolean check = this.servizioGiaPresente(servizio);
		try {
			if (check == true && nuovoPrezzo != null) {
				servizio.setPrezzo(nuovoPrezzo);
				servizioConModifiche = repository.save(servizio);
			} else if (check == true && nuovoPrezzo == null) {
				servizioConModifiche = servizio;
			} else {
				throw new NullPointerException();
			}
		} catch (NullPointerException | DataIntegrityViolationException e) {
			
		}
		return servizioConModifiche;
	}
	
	/**
	 * Metodo che consente di modificare la descrizione di un servizio offerto
	 * presente già sul database
	 */
	public Servizio modificaDescrizione(Servizio servizio, String nuovaDescrizione) {
		Servizio servizioConModifiche = null;
		Boolean check = this.servizioGiaPresente(servizio);
		try {
			if (check == true && nuovaDescrizione != null) {
				servizio.setDescrizione(nuovaDescrizione);
				servizioConModifiche = repository.save(servizio);
			} else if (check == true && nuovaDescrizione == null) {
				servizioConModifiche = servizio;
			} else {
				throw new NullPointerException();
			}
		} catch (NullPointerException e) {
			
		}
		return servizioConModifiche;
	}
	
	/**
	 * Metodo che consente di modificare la descrizione e il prezzo di un servizio offerto
	 * presente già sul database
	 */
	public Servizio modificaDescrizionePrezzo(Servizio servizio, String nuovaDescrizione, Double nuovoPrezzo) {
		Servizio servizioConModifiche = null;
		Boolean check = this.servizioGiaPresente(servizio);
		try {
			if (check == true && nuovaDescrizione != null && nuovoPrezzo != null) {
				servizio.setDescrizione(nuovaDescrizione);
				servizio.setPrezzo(nuovoPrezzo);
				servizioConModifiche = repository.save(servizio);
			} else if (check == true && (nuovaDescrizione == null || nuovoPrezzo == null)) {
				servizioConModifiche = servizio;
			} else {
				throw new NullPointerException();
			}
		} catch (NullPointerException | DataIntegrityViolationException e) {
			
		}
		return servizioConModifiche;
	}
	
	/**
	 * Metodo che consente di aggiungere un nuovo servizio al database
	 */
	public Servizio aggiungiServizio(Servizio servizio) {
		Servizio servizioDaAggiungere = null;
		Boolean check = this.servizioGiaPresente(servizio);
		try {
			if (check != true) {
				servizioDaAggiungere = repository.save(servizio);
			}
		} catch (NullPointerException | InvalidDataAccessApiUsageException e) {
			
		}
		return servizioDaAggiungere;
	}
	
	/**
	 * Metodo che consente di rimuovere un servizio dal database
	 */
	public void rimuoviServizio(Servizio servizio) {
		try {
			repository.delete(servizio);
		} catch (InvalidDataAccessApiUsageException e) {
			
		}
	}
	
	/**
	 * Metodo che consente di recuperare un servizio dal database
	 * in base al suo codice
	 */
	public Servizio cercaServizio(Long codice) {
		Servizio servizio = null;
		try {
			servizio = repository.findById(codice).get();
		} catch (InvalidDataAccessApiUsageException | NoSuchElementException e) {
			
		}
		return servizio;
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
	
	/**
	 * Metodo che ritorna un true se il servizio esiste, altrimenti ritorna false
	 */
	public Boolean servizioGiaPresente(Servizio servizio) {
		if (servizio == null) {
			return null;
		}
		return this.cercaServizioPerDescrizionePrezzo(servizio.getDescrizione(), servizio.getPrezzo()) != null;
	}
	
}
