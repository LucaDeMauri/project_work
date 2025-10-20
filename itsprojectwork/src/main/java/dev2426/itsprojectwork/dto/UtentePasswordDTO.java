package dev2426.itsprojectwork.dto;

import dev2426.itsprojectwork.models.Ruolo;

public class UtentePasswordDTO extends UtenteDTO{

	private long id;
	private String password;
	
	public UtentePasswordDTO(long id, byte[] immagine, String bio ,String nome, String cognome, String email, String password, Ruolo ruolo) {
		super(immagine, bio, nome, cognome, email, ruolo);
		this.id = id;
		this.password = password;
	}
	
	public UtentePasswordDTO() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
}
