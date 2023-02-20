package matteot92.prenotauncambiolook.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import matteot92.prenotauncambiolook.model.entities.Pagamento;
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

}
