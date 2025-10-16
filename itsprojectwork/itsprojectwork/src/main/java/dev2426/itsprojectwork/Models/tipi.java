package dev2426.itsprojectwork.Models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name="tipo")
public class tipi {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String verifica;
	private long idCompetenza;
	private long idAnnuncio;
	
}
