package dev2426.itsprojectwork.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev2426.itsprojectwork.Models.Competenza;
import dev2426.itsprojectwork.Repository.CompetenzeRepository;

@Service
public class CompetenzeService {

	@Autowired
	private CompetenzeRepository repository;
	
	public List<Competenza> getAll(){
		List<Competenza> elenco = null;
		for (Competenza e : repository.findAll()) {
			elenco.add(e);
		}
		return elenco;
	}
	
	public Optional<Competenza> getOne(Long id) {
		Optional<Competenza> elemento = null;
		elemento = repository.findById(id);
		return elemento;
	}
	
	public void insertOne(Competenza nuovo) {
		repository.save(nuovo);
	}
	
	public void deleteOne(Long id) {
		repository.deleteById(id);
	}
}
