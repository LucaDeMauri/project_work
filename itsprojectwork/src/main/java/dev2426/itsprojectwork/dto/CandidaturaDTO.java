package dev2426.itsprojectwork.dto;

import dev2426.itsprojectwork.models.StatoCandidatura;

public class CandidaturaDTO {
	private long id;
	private StatoCandidatura stato;
	private UtenteDTO utente;
	private AnnuncioDTO annuncio;
	
	public CandidaturaDTO() {}
	public CandidaturaDTO(long id, StatoCandidatura stato, UtenteDTO utente, AnnuncioDTO annuncio) {
		this.id = id;
		this.stato = stato;
		this.utente = utente;
		this.annuncio = annuncio;
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
	public UtenteDTO getUtente() {
		return utente;
	}
	public void setUtente(UtenteDTO utente) {
		this.utente = utente;
	}
	public AnnuncioDTO getAnnuncio() {
		return annuncio;
	}
	public void setAnnuncio(AnnuncioDTO annuncio) {
		this.annuncio = annuncio;
	}
	
	
}
