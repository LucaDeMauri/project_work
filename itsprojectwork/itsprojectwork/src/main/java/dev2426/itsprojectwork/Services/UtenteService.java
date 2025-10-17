package dev2426.itsprojectwork.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev2426.itsprojectwork.Models.Utente;
import dev2426.itsprojectwork.Repository.UtenteRepository;

@Service
public class UtenteService {
	@Autowired
	private UtenteRepository repository;
	
	public List<Utente> getAll(){
		return repository.findAll();
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
