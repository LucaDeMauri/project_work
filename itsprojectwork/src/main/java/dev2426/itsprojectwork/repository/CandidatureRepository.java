package dev2426.itsprojectwork.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev2426.itsprojectwork.models.Candidatura;
import dev2426.itsprojectwork.models.Utente;

//CandidatureRepository.java
@Repository
public interface CandidatureRepository extends JpaRepository<Candidatura, Long> {

    List<Candidatura> findByUtenteAndActiveTrue(Utente candidato);

    boolean existsByUtente_IdAndAnnuncio_IdAndActiveTrue(Long utenteId, Long annuncioId);
}
