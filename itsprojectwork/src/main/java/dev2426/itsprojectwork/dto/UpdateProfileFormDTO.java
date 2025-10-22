package dev2426.itsprojectwork.dto;



public class UpdateProfileFormDTO {

 private String nome;

 private String cognome;

 private String email;

 private String bio;

 // checkbox (o hidden) dal front se vuoi gestire la rimozione esplicita
 private Boolean removeImage;

 // getters/setters
 public String getNome() { return nome; }
 public void setNome(String nome) { this.nome = nome; }

 public String getCognome() { return cognome; }
 public void setCognome(String cognome) { this.cognome = cognome; }

 public String getEmail() { return email; }
 public void setEmail(String email) { this.email = email; }

 public String getBio() { return bio; }
 public void setBio(String bio) { this.bio = bio; }

 public Boolean getRemoveImage() { return removeImage; }
 public void setRemoveImage(Boolean removeImage) { this.removeImage = removeImage; }
}
