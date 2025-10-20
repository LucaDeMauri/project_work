package dev2426.itsprojectwork.dto;

public class AnnuncioDTO {
	private String tipoMansione;
	private String durata;
	private AziendaDTO azienda;
	private String location;
	
	public AnnuncioDTO() {}
	
	public AnnuncioDTO(String tipoMansione, String durata, AziendaDTO azienda, String location) {
		super();
		this.tipoMansione = tipoMansione;
		this.durata = durata;
		this.azienda = azienda;
		this.location = location;
	}
	public String getTipoMansione() {
		return tipoMansione;
	}
	public void setTipoMansione(String tipoMansione) {
		this.tipoMansione = tipoMansione;
	}
	public String getDurata() {
		return durata;
	}
	public void setDurata(String durata) {
		this.durata = durata;
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
	
	
}
