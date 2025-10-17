package dev2426.itsprojectwork.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev2426.itsprojectwork.Models.Azienda;
import dev2426.itsprojectwork.Repository.AziendeRepository;


@Service
public class AziendeService {
	@Autowired
	private AziendeRepository repository;
	
	public List<Azienda> getAll(){
		List<Azienda> elenco = null;
		
		for (Azienda e :repository.findAll()) {
			elenco.add(e);
		}
		
		return elenco;
	}
	
	public Optional<Azienda> getOne(Long id) {
		Optional<Azienda> elemento = null;
		elemento = repository.findById(id);
		return elemento;
	}
	
	public void insertOne(Azienda nuovo) {
		repository.save(nuovo);
	}
	
	public void deleteOne(Long id) {
		repository.deleteById(id);
	}
}
