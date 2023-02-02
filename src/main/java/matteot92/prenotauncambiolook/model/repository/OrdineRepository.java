package matteot92.prenotauncambiolook.model.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import matteot92.prenotauncambiolook.model.entities.Ordine;

public interface OrdineRepository extends CrudRepository<Ordine, Long> {

	public List<Ordine> findByData(LocalDate data);
	public List<Ordine> findByDataAndOrario(LocalDate data, LocalTime orario);
	@Query("SELECT COUNT(ordine) FROM Ordine ordine WHERE ordine.data = ?1")
	public Integer getPrenotazioniByData(LocalDate data);
	@Query("SELECT COUNT(ordine) FROM Ordine ordine WHERE ordine.data = ?1 AND ordine.orario = ?2")
	public Integer getPrenotazioniByDataAndOrario(LocalDate data, LocalTime orario);
	
}
