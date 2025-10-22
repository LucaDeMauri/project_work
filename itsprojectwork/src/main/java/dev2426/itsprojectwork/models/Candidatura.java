package dev2426.itsprojectwork.models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "candidature")
public class Candidatura {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private StatoCandidatura stato = StatoCandidatura.PENDING;

	@ManyToOne
	@JoinColumn(name = "id_annuncio")
	private Annuncio annuncio;

	@ManyToOne
	@JoinColumn(name = "id_utente")
	private Utente utente;

	@Column(name = "data_candidatura", insertable = false, updatable = false)
	private LocalDate dataCandidatura;
	
	@Column(name = "is_active")
    private boolean active = true;

	public Candidatura() {
	}

	public Candidatura(StatoCandidatura stato, Annuncio annuncio, Utente utente) {
		super();
		this.id = id;
		this.stato = stato;
		this.annuncio = annuncio;
		this.utente = utente;
		this.dataCandidatura = dataCandidatura;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public StatoCandidatura getStato() {
		return stato;
	}

	public void setStato(StatoCandidatura stato) {
		this.stato = stato;
	}

	public Annuncio getAnnuncio() {
		return annuncio;
	}

	public void setAnnuncio(Annuncio annuncio) {
		this.annuncio = annuncio;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public LocalDate getDataCandidatura() {
		return dataCandidatura;
	}

	public void setDataCandidatura(LocalDate dataCandidatura) {
		this.dataCandidatura = dataCandidatura;
	}

}
