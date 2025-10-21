package dev2426.itsprojectwork.dto;

import dev2426.itsprojectwork.models.StatoCandidatura;

public class CandidaturaDTO {
	private StatoCandidatura stato;
	private UtenteDTO utente;
	private AnnuncioDTO annuncio;
	
	public CandidaturaDTO() {}
	public CandidaturaDTO(StatoCandidatura stato, UtenteDTO utente, AnnuncioDTO annuncio) {
		super();
		this.stato = stato;
		this.utente = utente;
		this.annuncio = annuncio;
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
