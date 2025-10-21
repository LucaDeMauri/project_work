package dev2426.itsprojectwork.dto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import dev2426.itsprojectwork.models.Competenza;

public class AnnuncioDTO {
	private Long id;
	private String tipoMansione;
	private LocalDate data_inizio;
	private LocalDate data_fine;
	private AziendaDTO azienda;
	private String location;
	private String descrizione;
	private String orari;
	private String titolo;
	private Set<Competenza> competenzeRichieste;
	private Set<Competenza> competenzeAcquisite;

	
	public AnnuncioDTO() {}
	
	
	public AnnuncioDTO(Long id, String tipoMansione, LocalDate data_inizio, LocalDate data_fine, AziendaDTO azienda,
			String location, String descrizione, String orari, String titolo, Set<Competenza> competenzeRichieste,
			Set<Competenza> competenzeAcquisite) {
		super();
		this.id = id;
		this.tipoMansione = tipoMansione;
		this.data_inizio = data_inizio;
		this.data_fine = data_fine;
		this.azienda = azienda;
		this.location = location;
		this.descrizione = descrizione;
		this.orari = orari;
		this.titolo = titolo;
		this.competenzeRichieste = competenzeRichieste;
		this.competenzeAcquisite = competenzeAcquisite;
	}

	
	
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getTipoMansione() {
		return tipoMansione;
	}
	public void setTipoMansione(String tipoMansione) {
		this.tipoMansione = tipoMansione;
	}
	
	public LocalDate getData_inizio() {
		return data_inizio;
	}

	public void setData_inizio(LocalDate data_inizio) {
		this.data_inizio = data_inizio;
	}

	public LocalDate getData_fine() {
		return data_fine;
	}

	public void setData_fine(LocalDate data_fine) {
		this.data_fine = data_fine;
	}

	public AziendaDTO getAzienda() {
		return azienda;
	}
	public void setAzienda(AziendaDTO azienda) {
		this.azienda = azienda;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}


	public String getDescrizione() {
		return descrizione;
	}


	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}


	public String getOrari() {
		return orari;
	}


	public void setOrari(String orari) {
		this.orari = orari;
	}


	public String getTitolo() {
		return titolo;
	}


	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}


	public Set<Competenza> getCompetenzeRichieste() {
		return competenzeRichieste;
	}


	public void setCompetenzeRichieste(Set<Competenza> competenzeRichieste) {
		this.competenzeRichieste = competenzeRichieste;
	}


	public Set<Competenza> getCompetenzeAcquisite() {
		return competenzeAcquisite;
	}


	public void setCompetenzeAcquisite(Set<Competenza> competenzeAcquisite) {
		this.competenzeAcquisite = competenzeAcquisite;
	}
	
	
}
