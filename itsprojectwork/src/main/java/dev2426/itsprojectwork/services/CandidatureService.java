package dev2426.itsprojectwork.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev2426.itsprojectwork.dto.CandidaturaDTO;
import dev2426.itsprojectwork.dto.UtenteDTO;
import dev2426.itsprojectwork.models.Candidatura;
import dev2426.itsprojectwork.models.Utente;
import dev2426.itsprojectwork.repository.CandidatureRepository;

@Service
public class CandidatureService {
	@Autowired
	private CandidatureRepository repository;
	
	public List<Candidatura> getAll(){
		return repository.findAll();
	}
	
	public Optional<Candidatura> getOne(Long id) {
		return repository.findById(id);
	}
	
	public void insertOne(Candidatura nuovo) {
		repository.save(nuovo);
	}
	
	public void deleteOne(Long id) {
		repository.deleteById(id);
	}
	
	public List<CandidaturaDTO> getCandidaturaUtente(Utente candidato){
		ArrayList<CandidaturaDTO> elenco = new ArrayList<>();
		
		repository.findByCandidatoAndIsActiveTrue(candidato);
		
		for(CandidaturaDTO e: elenco) {
			elenco.add(new CandidaturaDTO(e.getId(),e.getStato(), e.getUtente(), e.getAnnuncio()));
		}
		return elenco;
	}
}
