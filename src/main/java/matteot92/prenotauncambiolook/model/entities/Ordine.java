package matteot92.prenotauncambiolook.model.entities;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "ordini")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Ordine {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_ordine")
	private Long id;
	@Column(nullable=false)
	private LocalDate data;
	@Column(nullable=false)
	private LocalTime orario;
	@Column(nullable=false)
	private Integer quantita;
	@ManyToOne
	@JoinColumn(name = "id_servizio")
	private Servizio servizio;
	@ManyToOne
	@JoinColumn(name = "id_utente")
	private Utente utente;
	
}
