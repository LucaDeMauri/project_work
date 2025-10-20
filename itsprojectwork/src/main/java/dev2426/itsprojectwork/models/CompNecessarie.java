package dev2426.itsprojectwork.models;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="job_skills_required")
public class CompNecessarie {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	private long idAnnuncio;
	private long idCompetenza;
	
	public CompNecessarie() {}
	
	public CompNecessarie(long id, long idAnnuncio, long idCompetenza) {
		this.id = id;
		this.idAnnuncio = idAnnuncio;
		this.idCompetenza = idCompetenza;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getIdAnnuncio() {
		return idAnnuncio;
	}
	public void setIdAnnuncio(long idAnnuncio) {
		this.idAnnuncio = idAnnuncio;
	}
	public long getIdCompetenza() {
		return idCompetenza;
	}
	public void setIdCompetenza(long idCompetenza) {
		this.idCompetenza = idCompetenza;
	}
	
	
	
	
	
}
