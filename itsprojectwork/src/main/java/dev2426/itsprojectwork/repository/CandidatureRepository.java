package dev2426.itsprojectwork.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
    
    @Query("""
            select distinct c.annuncio.id
            from Candidatura c
            where c.utente.id = :utenteId
              and c.active = true
            """)
     Set<Long> findAnnuncioIdsByUtenteAndActiveTrue(@Param("utenteId") Long utenteId);
    
    @Query("""
            SELECT c
            FROM Candidatura c
            JOIN FETCH c.annuncio a
            LEFT JOIN FETCH a.azienda az
            WHERE c.utente.id = :uid
              AND c.active = true
            ORDER BY c.id DESC
            """)
     List<Candidatura> findAllByUserIdFetchAll(@Param("uid") Long uid);
}