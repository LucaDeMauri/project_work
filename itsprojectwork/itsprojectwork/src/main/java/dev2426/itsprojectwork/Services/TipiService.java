package dev2426.itsprojectwork.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev2426.itsprojectwork.Models.Tipo;
import dev2426.itsprojectwork.Repository.TipiRepository;

@Service
public class TipiService {
	@Autowired
	private TipiRepository repository;
	
	public List<Tipo> getAll(){
		List<Tipo> elenco = null;
		for (Tipo e : repository.findAll()) {
			elenco.add(e);
		}
		return elenco;
	}
	
	public Optional<Tipo> getOne(Long id) {
		Optional<Tipo> elemento = null;
		elemento = repository.findById(id);
		return elemento;
	}
	
	public void insertOne(Tipo nuovo) {
		repository.save(nuovo);
	}
	
	public void deleteOne(Long id) {
		repository.deleteById(id);
	}
}
