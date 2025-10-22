package dev2426.itsprojectwork.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev2426.itsprojectwork.dto.AziendaDTO;
import dev2426.itsprojectwork.models.Azienda;
import dev2426.itsprojectwork.repository.AziendeRepository;


@Service
public class AziendeService {
	@Autowired
	private AziendeRepository repository;
	
	public List<Azienda> getAll(){
		return repository.findAll();
	}
	
//	public Optional<AziendaDTO> getOne(Long id) {
//		
//		Optional<Azienda> a = repository.findById(id);
//		
//		AziendaDTO aDTO = new AziendaDTO(a.get().getId(),a.get().getNome(), a.get().getAnnunci());
//		
//		return aDTO;
//	}
	
	public void insertOne(Azienda nuovo) {
		repository.save(nuovo);
	}
	
	public void deleteOne(Long id) {
		repository.deleteById(id);
	}
}
