package dev2426.itsprojectwork.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev2426.itsprojectwork.models.Annuncio;
import dev2426.itsprojectwork.models.Candidatura;
import dev2426.itsprojectwork.models.Utente;

@Repository
public interface CandidatureRepository extends JpaRepository<Candidatura, Long> {

    // Trova tutte le candidature attive per un dato Utente (Entity)
    List<Candidatura> findByUtenteAndActiveTrue(Utente utente); 

    // Trova tutte le candidature attive per un dato Annuncio (Entity)
    List<Candidatura> findByAnnuncioAndActiveTrue(Annuncio annuncio);

    // Verifica se l'Utente ha già una Candidatura attiva per l'Annuncio (Perfetto!)
    boolean existsByUtente_IdAndAnnuncio_IdAndActiveTrue(Long utenteId, Long annuncioId);
}