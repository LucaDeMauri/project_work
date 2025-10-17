package dev2426.itsprojectwork.Models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="azienda")
public class Azienda {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	
	private String nome;
	
	@OneToMany(mappedBy = "azienda", cascade = CascadeType.ALL)
    private List<Annuncio> annunci;
	
}
