package matteot92.prenotauncambiolook.model.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import matteot92.prenotauncambiolook.model.entities.Ordine;

public interface OrdineRepository extends CrudRepository<Ordine, Long> {

	public List<Ordine> findAll();
	public Ordine save(Ordine ordine);
}
