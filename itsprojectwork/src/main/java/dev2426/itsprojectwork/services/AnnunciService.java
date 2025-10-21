package dev2426.itsprojectwork.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev2426.itsprojectwork.dto.AnnuncioDTO;
import dev2426.itsprojectwork.dto.AziendaDTO;
import dev2426.itsprojectwork.models.Annuncio;
import dev2426.itsprojectwork.repository.AnnunciRepository;

@Service
public class AnnunciService {

	@Autowired
	private AnnunciRepository repository;
	
	public List<AnnuncioDTO> getAll() {

	    List<AnnuncioDTO> elenco = new ArrayList<>();

	    for (Annuncio e : repository.findAll()) {
	        elenco.add(new AnnuncioDTO(
	            e.getTipoMansione(),
	            e.getDataInizio(),
	            e.getDataFine(),
	            new AziendaDTO(e.getAzienda().getNome(), null),
	            e.getLocation(),
	            e.getDescrizione(),
	            e.getOrari(),
	            e.getTitolo(),
	            e.getCompetenzeRichieste(),
	            e.getCompetenzeAcquisite()
	        ));
	    }
	    
	    return elenco;
	}

	
	public Optional<Annuncio> getOne(Long id) {
		
		Optional<Annuncio> elemento = null;
		elemento = repository.findById(id);
		
		return elemento;
	}
	
	public void insertOne(Annuncio nuovo) {
		repository.save(nuovo);
	}
	
	public void deleteOne(Long id) {
		repository.deleteById(id);
	}
}
