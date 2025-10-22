package dev2426.itsprojectwork.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev2426.itsprojectwork.dto.AnnuncioDTO;
import dev2426.itsprojectwork.dto.CandidaturaDTO;
import dev2426.itsprojectwork.dto.UtenteDTO;
import dev2426.itsprojectwork.mapper.DtoMapper;
import dev2426.itsprojectwork.models.Annuncio;
import dev2426.itsprojectwork.models.Candidatura;
import dev2426.itsprojectwork.models.EsitoCandidatura;
import dev2426.itsprojectwork.models.Utente;
import dev2426.itsprojectwork.repository.CandidatureRepository;
import jakarta.transaction.Transactional;
import dev2426.itsprojectwork.models.StatoCandidatura;

@Service
public class CandidatureService {
    @Autowired
    private CandidatureRepository repository;
    
    @Autowired
    private AnnunciService servizioAnnunci;
    
    @Autowired
    private UtenteService servizioUtente;

    public List<CandidaturaDTO> getAll() {
        return repository.findAll().stream()
                         .map(DtoMapper::toCandidaturaDTO)
                         .collect(Collectors.toList());
    }
    
    public Optional<CandidaturaDTO> getOne(Long id) {
        return repository.findById(id)
                         .map(DtoMapper::toCandidaturaDTO);
    }
    
    
    public EsitoCandidatura insertOne(Long idAnnuncio, Long idUtente) {
    	 if (this.isAlreadyCandidato(idUtente, idAnnuncio)) return EsitoCandidatura.DUPLICATA;

         AnnuncioDTO annuncioDTO = servizioAnnunci.getOne(idAnnuncio);
         if (annuncioDTO == null) return EsitoCandidatura.ANNUNCIO_NON_TROVATO;

         UtenteDTO utenteDTO = servizioUtente.getOne(idUtente);
         if (utenteDTO == null) return EsitoCandidatura.ERRORE;

         Annuncio annuncio = DtoMapper.toAnnuncioEntity(annuncioDTO);
         Utente utente = DtoMapper.toUtenteEntity(utenteDTO);

         Candidatura candidatura = new Candidatura(StatoCandidatura.PENDING, annuncio, utente);
         repository.save(candidatura);
         return EsitoCandidatura.INSERITA;
    }

    
    public void deleteOne(Long id) {
        repository.deleteById(id);
    }
    
    public boolean isAlreadyCandidato(Long utenteId, Long annuncioId) {
        return repository.existsByUtente_IdAndAnnuncio_IdAndActiveTrue(utenteId, annuncioId);
    }


    public List<CandidaturaDTO> getActiveCandidaturesByUtente(Long utenteId) {
        UtenteDTO utenteDTO = servizioUtente.getOne(utenteId);
        Utente candidato = DtoMapper.toUtenteEntity(utenteDTO);
        
        return repository.findByUtenteAndActiveTrue(candidato).stream()
                         .map(DtoMapper::toCandidaturaDTO)
                         .collect(Collectors.toList());
    }
    
    public List<CandidaturaDTO> getActiveCandidaturesByAnnuncio(Long annuncioId) {
        
        AnnuncioDTO annuncioDTO = servizioAnnunci.getOne(annuncioId);
        
        if (annuncioDTO == null) {
            return List.of(); 
        }
        
        Annuncio annuncio = DtoMapper.toAnnuncioEntity(annuncioDTO);
        
        return repository.findByAnnuncioAndActiveTrue(annuncio).stream()
                         .map(DtoMapper::toCandidaturaDTO)
                         .collect(Collectors.toList());
    }

    public Set<Long> idsAnnunciGiaCandidato(long utenteId) {
        if (utenteId <= 0) return Collections.emptySet();

        Set<Long> ids = repository.findAnnuncioIdsByUtenteAndActiveTrue(utenteId);
        if (ids != null && !ids.isEmpty()) return ids;
        return null;

    }
    
    public List<CandidaturaDTO> findForUserWithAnnunci(long userId) {
    	
        ArrayList<Candidatura> elenco = (ArrayList<Candidatura>) repository.findAllByUserIdFetchAll(userId);
        ArrayList<CandidaturaDTO> elencoDTO = new ArrayList();
        
        for(Candidatura c : elenco) {
        	elencoDTO.add(DtoMapper.toCandidaturaDTO(c));
        }
        
        return elencoDTO;
    }
    
    public void updateStato(Long candidaturaId, StatoCandidatura nuovoStato) {
        
        Optional<Candidatura> candidatura = repository.findById(candidaturaId);
           
        candidatura.get().setStato(nuovoStato);
 
        repository.save(candidatura.get());
    }
}