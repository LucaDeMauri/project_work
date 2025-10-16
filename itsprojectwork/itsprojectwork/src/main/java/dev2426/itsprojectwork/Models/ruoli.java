package dev2426.itsprojectwork.Models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name="ruoli")
public class ruoli {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String tipo;
    private long id_utente;

    public ruoli(long id,String tipo,long id_utente){
        this.id=id;
        this.tipo=tipo;
        this.id_utente=id_utente;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public long getId_utente() {
        return id_utente;
    }
    public void setId_utente(long id_utente) {
        this.id_utente = id_utente;
    }

}
