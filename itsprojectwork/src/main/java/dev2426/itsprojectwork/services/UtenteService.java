package dev2426.itsprojectwork.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev2426.itsprojectwork.dto.UtenteDTO;
import dev2426.itsprojectwork.models.Utente;
import dev2426.itsprojectwork.repository.UtenteRepository;

@Service
public class UtenteService {
	@Autowired
	private UtenteRepository repository;
	
	public List<UtenteDTO> getAll(){
		
		List<Utente> elenco = repository.findAll();
		ArrayList<UtenteDTO> elencoDTO = new ArrayList<>();
		for (Utente e : elenco) {
			elencoDTO.add(new UtenteDTO(e.getNome(),e.getCognome(),e.getEmail(),e.getRuolo()));
		}
		
		return null;
	}
	
	public Optional<Utente> getOne(Long id) {
		return repository.findById(id);
	}
	
	public void insertOne(Utente nuovo) {
		repository.save(nuovo);
	}
	
	public void deleteOne(Long id) {
		repository.deleteById(id);
	}
}
