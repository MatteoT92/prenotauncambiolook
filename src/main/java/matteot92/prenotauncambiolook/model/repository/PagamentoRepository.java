package matteot92.prenotauncambiolook.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import matteot92.prenotauncambiolook.model.entities.Pagamento;

@Repository
public interface PagamentoRepository extends CrudRepository<Pagamento, Long> {
	
	public List<Pagamento> findByUtente(Long utente);
	public Optional<Pagamento> findByOrdine(Long ordine);
	public Optional<Pagamento> findByUtenteAndOrdine(Long Utente, Long ordine);

}
