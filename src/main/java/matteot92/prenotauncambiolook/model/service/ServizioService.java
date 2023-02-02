package matteot92.prenotauncambiolook.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import matteot92.prenotauncambiolook.model.entities.Servizio;
import matteot92.prenotauncambiolook.model.entities.Utente;
import matteot92.prenotauncambiolook.model.repository.ServizioRepository;

@Service
public class ServizioService {
	
	private ServizioRepository repository;
	
	@Autowired
	public ServizioService(ServizioRepository repository) {
		this.repository = repository;
	}
	
	public List<Servizio> serviziOfferti() {
		return (List<Servizio>) repository.findAll();
	}
	
	public Servizio modificaPrezzo(Servizio servizio, Double nuovoPrezzo) {
		servizio.setPrezzo(nuovoPrezzo);
		return repository.save(servizio);
	}
	
	public Servizio modificaDescrizione(Servizio servizio, String nuovaDescrizione) {
		servizio.setDescrizione(nuovaDescrizione);
		return repository.save(servizio);
	}
	
	public Servizio modificaDescrizionePrezzo(Servizio servizio, String nuovaDescrizione, Double nuovoPrezzo) {
		servizio.setDescrizione(nuovaDescrizione);
		servizio.setPrezzo(nuovoPrezzo);
		return repository.save(servizio);
	}
	
	public Servizio aggiungiServizio(Servizio servizio) {
		return repository.save(servizio);
	}
	
	public void rimuoviServizio(Servizio servizio) {
		repository.delete(servizio);
	}
	
	public Servizio cercaServizio(Long codice) {
		return repository.findById(codice).get();
	}
	
	public Servizio cercaServizioPerDescrizione(String descrizione) {
		return repository.findByDescrizione(descrizione);
	}
	
	public List<Servizio> cercaServizioPerPrezzo(Double prezzo) {
		return repository.findByPrezzo(prezzo);
	}
	
	public Servizio cercaServizioPerDescrizionePrezzo(String descrizione, Double prezzo) {
		return repository.findByDescrizioneAndPrezzo(descrizione, prezzo);
	}

}
