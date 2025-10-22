package dev2426.itsprojectwork.dto;

import dev2426.itsprojectwork.models.Ruolo;

public class UtentePasswordDTO extends UtenteDTO{

	private String password;
	
	public UtentePasswordDTO(long id, String immagine, String bio ,String nome, String cognome, String email, String password, Ruolo ruolo) {
		super(id, immagine, bio, nome, cognome, email, ruolo);
		this.password = password;
	}
	
	public UtentePasswordDTO() {}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
}
