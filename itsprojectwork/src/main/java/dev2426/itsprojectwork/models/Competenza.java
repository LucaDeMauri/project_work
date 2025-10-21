package dev2426.itsprojectwork.models;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "competenze")
public class Competenza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descrizione;

<<<<<<< Updated upstream
<<<<<<< Updated upstream
    @ManyToMany(mappedBy = "competenzeRichieste", fetch = FetchType.LAZY)
    private Set<Annuncio> annunciRichieste = new HashSet<>();

    @ManyToMany(mappedBy = "competenzeAcquisite", fetch = FetchType.LAZY)
=======
    @ManyToMany(mappedBy = "competenzeRichieste")
    private Set<Annuncio> annunciRichieste = new HashSet<>();

    @ManyToMany(mappedBy = "competenzeAcquisite")
>>>>>>> Stashed changes
=======
    @ManyToMany(mappedBy = "competenzeRichieste")
    private Set<Annuncio> annunciRichieste = new HashSet<>();

    @ManyToMany(mappedBy = "competenzeAcquisite")
>>>>>>> Stashed changes
    private Set<Annuncio> annunciAcquisite = new HashSet<>();

    public Competenza() {}

    public Competenza(String descrizione) {
        this.descrizione = descrizione;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Set<Annuncio> getAnnunciRichieste() {
        return annunciRichieste;
    }

    public void setAnnunciRichieste(Set<Annuncio> annunciRichieste) {
        this.annunciRichieste = annunciRichieste;
    }

    public Set<Annuncio> getAnnunciAcquisite() {
        return annunciAcquisite;
    }

    public void setAnnunciAcquisite(Set<Annuncio> annunciAcquisite) {
        this.annunciAcquisite = annunciAcquisite;
    }

    
}
