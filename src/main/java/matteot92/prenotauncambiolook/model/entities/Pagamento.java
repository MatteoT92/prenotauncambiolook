package matteot92.prenotauncambiolook.model.entities;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "pagamenti")
@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonPropertyOrder({ "id", "data", "ordine", "utente", "importo"})
public class Pagamento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pagamento")
	private Long id;
	@Column(nullable=false)
	private LocalDate data;
	@Column(nullable=false,
			name = "id_ordine")
	private Long ordine;
	@Column(nullable=false,
			name = "id_utente")
	private Long utente;
	@Column(nullable=false,
			name = "importo")
	private Double importo;

}