package dev2426.itsprojectwork.dto;

public class CandidaturaDTO {
	private String stato;
	private UtenteDTO utente;
	private AnnuncioDTO annuncio;
	
	public CandidaturaDTO() {}
	public CandidaturaDTO(String stato, UtenteDTO utente, AnnuncioDTO annuncio) {
		super();
		this.stato = stato;
		this.utente = utente;
		this.annuncio = annuncio;
	}
	public String getStato() {
		return stato;
	}
	public void setStato(String stato) {
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
