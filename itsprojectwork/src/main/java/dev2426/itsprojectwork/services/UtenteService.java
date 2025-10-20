package dev2426.itsprojectwork.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev2426.itsprojectwork.dto.UtenteDTO;
import dev2426.itsprojectwork.dto.UtentePasswordDTO;
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
			elencoDTO.add(new UtenteDTO(e.getId(),e.getImmagine(),e.getNome(),e.getCognome(),e.getEmail(),e.getRuolo()));
		}
		
		return elencoDTO;
	}
	
	public UtenteDTO getOne(Long id) {
	    Optional<Utente> user = repository.findById(id);

	    if (user.isPresent()) {
	        Utente u = user.get();
	        return new UtenteDTO(u.getId(), u.getImmagine(), u.getNome(), u.getCognome(), u.getEmail(), u.getRuolo());
	    }

	    return null;
	}
	
	public void insertOne(UtentePasswordDTO nuovo) {
		
		Utente user = new Utente(nuovo.getId(),nuovo.getImmagine(), nuovo.getNome(), nuovo.getCognome(), nuovo.getEmail(), nuovo.getPassword(), nuovo.getRuolo()); 
		
		repository.save(user);
	}
	
	public void deleteOne(Long id) {
		repository.deleteById(id);
	}
}
