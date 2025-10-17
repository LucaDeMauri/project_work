package dev2426.itsprojectwork.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity

@Table(name="annunci")
public class Annuncio {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private long id;
	
	@Getter
	@Setter
	@Column(name="tipo_mansione")
	private String tipoMansione;
	
	@Getter
	@Setter
	private int durata;
	
	@Getter
	@Setter
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idAzienda")
	private Azienda azienda;
	
	
	
}
