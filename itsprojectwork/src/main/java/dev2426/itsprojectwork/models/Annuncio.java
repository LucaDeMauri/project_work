package dev2426.itsprojectwork.models;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import dev2426.itsprojectwork.dto.AziendaDTO;

@Entity
@Table(name = "annunci")
public class Annuncio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String titolo;

	@Column(name = "tipo_mansione")
	private String tipoMansione;

	private String location;

	@Column(columnDefinition = "TEXT")
	private String descrizione;

	private String orari;

	private boolean isActive;

	private LocalDate dataInizio;
	private LocalDate dataFine;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idAzienda")
	private Azienda azienda;

	@ManyToMany
	@JoinTable(name = "annuncio_competenze_richieste", joinColumns = @JoinColumn(name = "annuncio_id"), inverseJoinColumns = @JoinColumn(name = "competenza_id"))
	private Set<Competenza> competenzeRichieste = new HashSet<>();

	@ManyToMany
	@JoinTable(name = "annuncio_competenze_acquisite", joinColumns = @JoinColumn(name = "annuncio_id"), inverseJoinColumns = @JoinColumn(name = "competenza_id"))
	private Set<Competenza> competenzeAcquisite = new HashSet<>();

	public Annuncio() {
	}

	public Annuncio(String titolo, String tipoMansione, String location, String descrizione, String orari,
			boolean isActive, LocalDate dataInizio, LocalDate dataFine, Azienda azienda) {
		this.titolo = titolo;
		this.tipoMansione = tipoMansione;
		this.location = location;
		this.descrizione = descrizione;
		this.orari = orari;
		this.isActive = isActive;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
		this.azienda = azienda;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getTipoMansione() {
		return tipoMansione;
	}

	public void setTipoMansione(String tipoMansione) {
		this.tipoMansione = tipoMansione;
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

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean active) {
		isActive = active;
	}

	public LocalDate getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(LocalDate dataInizio) {
		this.dataInizio = dataInizio;
	}

	public LocalDate getDataFine() {
		return dataFine;
	}

	public void setDataFine(LocalDate dataFine) {
		this.dataFine = dataFine;
	}

	public Azienda getAzienda() {
		return azienda;
	}

	public void setAzienda(Azienda azienda) {
		this.azienda = azienda;
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
