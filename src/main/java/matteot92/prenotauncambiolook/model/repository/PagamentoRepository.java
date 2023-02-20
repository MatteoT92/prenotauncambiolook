package matteot92.prenotauncambiolook.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import matteot92.prenotauncambiolook.model.entities.Pagamento;

@Repository
public interface PagamentoRepository extends CrudRepository<Pagamento, Long> {
	
	

}
