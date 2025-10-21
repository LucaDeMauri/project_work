package dev2426.itsprojectwork.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev2426.itsprojectwork.dto.AnnuncioDTO;
import dev2426.itsprojectwork.dto.AziendaDTO;
import dev2426.itsprojectwork.dto.CandidaturaDTO;
import dev2426.itsprojectwork.dto.UtenteDTO;
import dev2426.itsprojectwork.mapper.DtoMapper;
import dev2426.itsprojectwork.models.Annuncio;
import dev2426.itsprojectwork.models.Candidatura;
import dev2426.itsprojectwork.models.StatoCandidatura;
import dev2426.itsprojectwork.models.Utente;
import dev2426.itsprojectwork.repository.CandidatureRepository;

@Service
public class CandidatureService {
	@Autowired
	private CandidatureRepository repository;
	
	@Autowired
	private AnnunciService servizioAnnunci;
	
	@Autowired
	private UtenteService servizioUtente;
	
	public List<Candidatura> getAll(){
		return repository.findAll();
	}
	
	public Optional<Candidatura> getOne(Long id) {
		return repository.findById(id);
	}
	
	public void insertOne(Long idAnnuncio, Long idUtente) {
		
		AnnuncioDTO annuncioDTO = (AnnuncioDTO) servizioAnnunci.getOne(idAnnuncio);
		UtenteDTO utenteDTO = servizioUtente.getOne(idUtente);
		
		Annuncio annuncio = DtoMapper.toAnnuncioEntity(annuncioDTO);
		Utente utente = DtoMapper.toUtenteEntity(utenteDTO);
		
		Candidatura candidatura = new Candidatura(StatoCandidatura.PENDING, annuncio, utente);
		System.out.println(candidatura.getStato());
		System.out.println(candidatura.getAnnuncio());
		
		repository.save(candidatura);
		
	}
	
	public void deleteOne(Long id) {
		repository.deleteById(id);
	}
	
	public List<CandidaturaDTO> getCandidatureUtente(UtenteDTO candidatoDTO){
		ArrayList<CandidaturaDTO> elenco = new ArrayList<>();
		

		Utente candidato = new Utente();
		candidato.setId(candidatoDTO.getId());
		candidato.setNome(candidatoDTO.getNome());
		candidato.setCognome(candidatoDTO.getCognome());
		candidato.setEmail(candidatoDTO.getEmail());
		candidato.setBio(candidatoDTO.getBio());
		
		
		repository.findByUtenteAndActiveTrue(candidato);
		
		for(CandidaturaDTO e: elenco) {
			elenco.add(new CandidaturaDTO(e.getId(),e.getStato(), e.getUtente(), e.getAnnuncio()));
		}
		return elenco;
	}
}
