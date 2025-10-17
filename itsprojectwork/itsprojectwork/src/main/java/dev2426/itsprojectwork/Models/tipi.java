package dev2426.itsprojectwork.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Data
@Table(name="tipo")
public class tipi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String verifica;
    private long id_competenza;
    private long id_annuncio;

    public tipi(long id,String verifica,long id_competenza,long id_annuncio){
        this.id=id;
        this.verifica=verifica;
        this.id_competenza=id_competenza;
        this.id_annuncio=id_annuncio;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getVerifica() {
        return verifica;
    }
    public void setVerifica(String verifica) {
        this.verifica = verifica;
    }

    public long getId_competenza() {
        return id_competenza;
    }
    public void setId_competenza(long id_competenza) {
        this.id_competenza = id_competenza;
    }

    public long getId_annuncio() {
        return id_annuncio;
    }
    public void setId_annuncio(long id_annuncio) {
        this.id_annuncio = id_annuncio;
    }
}
