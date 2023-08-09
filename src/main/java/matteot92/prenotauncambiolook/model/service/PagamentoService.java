package matteot92.prenotauncambiolook.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import matteot92.prenotauncambiolook.model.entities.Ordine;
import matteot92.prenotauncambiolook.model.entities.Pagamento;
import matteot92.prenotauncambiolook.model.entities.Utente;
import matteot92.prenotauncambiolook.model.repository.PagamentoRepository;

@Service
public class PagamentoService {
	
	private PagamentoRepository repository;
	
	@Autowired
	public PagamentoService(PagamentoRepository repository) {
		this.repository = repository;
	}
	
	/**
	 * Metodo che effettua la registrazione di un pagamento
	 */
	public Pagamento effettuaPagamento(Pagamento pagamento) {
		return repository.save(pagamento);
	}
	
	/**
	 * Metodo che mostra i pagamenti effettuati da un utente cliente
	 */
	public List<Pagamento> pagamentiEffettuati(Utente utente) {
		return (List<Pagamento>) repository.findByUtente(utente.getId());
	}
	
	/**
	 * Metodo che ritorna le informazioni del pagamento effettuato per quel dato ordine
	 */
	public Pagamento pagamentoEffettuato(Ordine ordine) {
		return repository.findByOrdine(ordine.getId()).get();
	}
	
	/**
	 * Metodo che ritorna le informazioni del pagamento effettuato per quel dato utente cliente e ordine
	 */
	public Pagamento pagamentoEffettuato(Utente utente, Ordine ordine) {
		return repository.findByUtenteAndOrdine(utente.getId(), ordine.getId()).get();
	}
	
}
