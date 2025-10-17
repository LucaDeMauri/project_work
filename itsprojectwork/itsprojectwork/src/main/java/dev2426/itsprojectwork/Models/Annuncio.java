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

@Entity
@Data
@Table(name="annunci")
public class Annuncio {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="tipo_mansione")
	private String tipoMansione;
	
	private int durata;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idAzienda")
	private Azienda azienda;

}
