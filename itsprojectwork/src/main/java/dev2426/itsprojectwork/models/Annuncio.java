package dev2426.itsprojectwork.models;

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
	
	@Column(name="tipo_mansione")
	private String tipoMansione;
	
	private String durata;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idAzienda")
	private Azienda azienda;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTipoMansione() {
		return tipoMansione;
	}

	public void setTipoMansione(String tipoMansione) {
		this.tipoMansione = tipoMansione;
	}


	public Azienda getAzienda() {
		return azienda;
	}

	public void setAzienda(Azienda azienda) {
		this.azienda = azienda;
	}
	
	
	
	
	
}
