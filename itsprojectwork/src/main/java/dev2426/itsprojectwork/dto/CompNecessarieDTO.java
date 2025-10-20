package dev2426.itsprojectwork.dto;


public class CompNecessarieDTO {
	private AnnuncioDTO annuncio;
	private CompetenzaDTO competenza;
	
	public CompNecessarieDTO() {}
	
	public CompNecessarieDTO(AnnuncioDTO annuncio, CompetenzaDTO competenza) {
		this.annuncio = annuncio;
		this.competenza = competenza;
	}

	public AnnuncioDTO getAnnuncio() {
		return annuncio;
	}

	public void setAnnuncio(AnnuncioDTO annuncio) {
		this.annuncio = annuncio;
	}

	public CompetenzaDTO getCompetenza() {
		return competenza;
	}

	public void setCompetenza(CompetenzaDTO competenza) {
		this.competenza = competenza;
	}
	
	
}
