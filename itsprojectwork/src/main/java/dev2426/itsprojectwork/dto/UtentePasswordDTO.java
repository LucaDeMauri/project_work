package dev2426.itsprojectwork.dto;

import dev2426.itsprojectwork.models.Ruolo;

public class UtentePasswordDTO {
	private long id;
	private byte[] immagine;
	private String nome;
	private String cognome;
	private String email;
	private String password;
	private Ruolo ruolo;
	
	public UtentePasswordDTO(long id, byte[] immagine, String nome, String cognome, String email, String password, Ruolo ruolo) {
		this.id = id;
		this.immagine = immagine;
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.password = password;
		this.ruolo = ruolo;
	}
	
	public UtentePasswordDTO() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public byte[] getImmagine() {
		return immagine;
	}

	public void setImmagine(byte[] immagine) {
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
}
