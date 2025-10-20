package dev2426.itsprojectwork.dto;

import dev2426.itsprojectwork.models.Ruolo;

public class UtenteDTO {
	private long id;
	private String immagine;
	private String nome;
	private String cognome;
	private String email;
	private Ruolo ruolo;
	
	public UtenteDTO(String nome, String cognome, String email, Ruolo ruolo) {
		this.immagine = immagine;
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.ruolo = ruolo;
	}
	
	public UtenteDTO() {}

	public String getImmagine() {
		return immagine;
	}

	public void setImmagine(String immagine) {
		this.immagine = immagine;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Ruolo getRuolo() {
		return ruolo;
	}

	public void setRuolo(Ruolo ruolo) {
		this.ruolo = ruolo;
	}

	
}
