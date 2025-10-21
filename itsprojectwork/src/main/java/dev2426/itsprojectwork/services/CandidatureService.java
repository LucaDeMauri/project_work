package dev2426.itsprojectwork.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev2426.itsprojectwork.dto.AnnuncioDTO;
import dev2426.itsprojectwork.dto.CandidaturaDTO;
import dev2426.itsprojectwork.dto.UtenteDTO;
import dev2426.itsprojectwork.mapper.DtoMapper;
import dev2426.itsprojectwork.models.Annuncio;
import dev2426.itsprojectwork.models.Candidatura;
import dev2426.itsprojectwork.models.Utente;
import dev2426.itsprojectwork.repository.CandidatureRepository;
import dev2426.itsprojectwork.models.StatoCandidatura;

@Service
public class CandidatureService {
    @Autowired
    private CandidatureRepository repository;
    
    @Autowired
    private AnnunciService servizioAnnunci;
    
    @Autowired
    private UtenteService servizioUtente;
    
    // --- Metodi CRUD Base ---
    
    public List<CandidaturaDTO> getAll() {
        return repository.findAll().stream()
                         .map(DtoMapper::toCandidaturaDTO)
                         .collect(Collectors.toList());
    }
    
    public Optional<CandidaturaDTO> getOne(Long id) {
        return repository.findById(id)
                         .map(DtoMapper::toCandidaturaDTO);
    }
    
    /**
     * Inserisce una nuova candidatura dopo aver verificato che non ne esista già una attiva.
     */
    public CandidaturaDTO insertOne(Long idAnnuncio, Long idUtente) {
        // 1. Validazione Business: Verifica se una candidatura ATTIVA esiste già
        if (this.isAlreadyCandidato(idUtente, idAnnuncio)) {
            throw new IllegalStateException("L'utente ha già una candidatura attiva per questo annuncio.");
        }
        
        // 2. Recupero DTO usando il metodo originale di AnnunciService (che restituisce DTO)
        AnnuncioDTO annuncioDTO = servizioAnnunci.getOne(idAnnuncio);
        UtenteDTO utenteDTO = servizioUtente.getOne(idUtente);
        
        // 3. Conversione DTO a Entity
        Annuncio annuncio = DtoMapper.toAnnuncioEntity(annuncioDTO);
        Utente utente = DtoMapper.toUtenteEntity(utenteDTO);
        
        // 4. Creazione e Salvataggio 
        Candidatura candidatura = new Candidatura(StatoCandidatura.PENDING, annuncio, utente);
        candidatura = repository.save(candidatura);
        
        // 5. Mappatura e restituzione del DTO salvato
        return DtoMapper.toCandidaturaDTO(candidatura);
    }
    
    public void deleteOne(Long id) {
        repository.deleteById(id);
    }
    
    // --- Nuovi Metodi Derivati dalla Repository ---

    /**
     * Verifica l'esistenza di una candidatura attiva per l'utente e l'annuncio specifici.
     */
    public boolean isAlreadyCandidato(Long utenteId, Long annuncioId) {
        return repository.existsByUtente_IdAndAnnuncio_IdAndActiveTrue(utenteId, annuncioId);
    }

    /**
     * Restituisce tutte le candidature ATTIVE per un utente specifico (Candidato).
     */
    public List<CandidaturaDTO> getActiveCandidaturesByUtente(Long utenteId) {
        // 1. Carico l'Utente Entity
        UtenteDTO utenteDTO = servizioUtente.getOne(utenteId);
        Utente candidato = DtoMapper.toUtenteEntity(utenteDTO);
        
        // 2. Chiamata alla repository e mappatura
        return repository.findByUtenteAndActiveTrue(candidato).stream()
                         .map(DtoMapper::toCandidaturaDTO)
                         .collect(Collectors.toList());
    }
    
    /**
     * Restituisce tutte le candidature ATTIVE per un annuncio specifico (Azienda).
     * Recupera l'Annuncio tramite la repository locale per ottenere l'Entity.
     */
    public List<CandidaturaDTO> getActiveCandidaturesByAnnuncio(Long annuncioId) {
        
        // 1. Dobbiamo recuperare l'Entity Annuncio direttamente dalla repository Annunci
        //    (Dato che AnnunciService non restituisce l'Entity, lavoriamo qui)
        
        // ATTENZIONE: Questo richiede l'iniezione della AnnunciRepository. 
        // Se non la vuoi iniettare qui, l'unico modo è che AnnunciService esponga l'Entity.
        
        // SOLUZIONE ALTERNATIVA (Migliore): Recuperiamo il DTO e lo convertiamo in Entity
        AnnuncioDTO annuncioDTO = servizioAnnunci.getOne(annuncioId);
        
        if (annuncioDTO == null) {
            return List.of(); 
        }
        
        Annuncio annuncio = DtoMapper.toAnnuncioEntity(annuncioDTO);
        
        // 2. Chiamata alla repository e mappatura
        return repository.findByAnnuncioAndActiveTrue(annuncio).stream()
                         .map(DtoMapper::toCandidaturaDTO)
                         .collect(Collectors.toList());
    }
}