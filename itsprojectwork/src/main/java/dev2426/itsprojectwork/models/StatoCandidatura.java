package dev2426.itsprojectwork.models;

public enum StatoCandidatura {
	PENDING("In attesa"),
    ACCETTATO("Accettato"),
    RIFIUTATO("Rifiutato");
    
    private String descrizione;
    
    StatoCandidatura(String descrizione) {
        this.descrizione = descrizione;
    }
    
    public String getDescrizione() {
        return descrizione;
    }
}
