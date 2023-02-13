package matteot92.prenotauncambiolook.model.entities;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.CascadeType;
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
@JsonPropertyOrder({ "id", "data", "orario", "quantita", "servizio", "utente"})
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
	/*
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "id_servizio")
	private Servizio servizio;
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "id_utente")
	private Utente utente;
	*/
	@Column(name="id_servizio")
	private Long servizio;
	@Column(name="id_utente")
	private Long utente;
	
}
