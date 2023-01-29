package matteot92.prenotauncambiolook.model.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import matteot92.prenotauncambiolook.model.entities.Ordine;

public interface OrdineRepository extends CrudRepository<Ordine, Long> {

	public List<Ordine> findByData(LocalDate data);
	public List<Ordine> findByDataAndOrario(LocalDate data, LocalTime orario);

}
