package dev2426.itsprojectwork.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev2426.itsprojectwork.Models.Ruolo;
import dev2426.itsprojectwork.Repository.RuoliRepository;


@Service
public class RuoliService {
	@Autowired
	private RuoliRepository repository;
	
	public List<Ruolo> getAll(){
		
		List<Ruolo> elenco = null;
		for (Ruolo e : repository.findAll()) {
			elenco.add(e);
		}
		return elenco;
		
	}
	
	public Optional<Ruolo> getOne(Long id) {
		Optional<Ruolo> elemento = null;
		elemento = repository.findById(id);
		return elemento;
	}
	
	public void insertOne(Ruolo nuovo) {
		repository.save(nuovo);
	}
	
	public void deleteOne(Long id) {
		repository.deleteById(id);
	}
}
