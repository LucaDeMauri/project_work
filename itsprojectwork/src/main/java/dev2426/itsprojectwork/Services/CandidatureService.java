package dev2426.itsprojectwork.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev2426.itsprojectwork.Models.Candidatura;
import dev2426.itsprojectwork.Repository.CandidatureRepository;

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
}
