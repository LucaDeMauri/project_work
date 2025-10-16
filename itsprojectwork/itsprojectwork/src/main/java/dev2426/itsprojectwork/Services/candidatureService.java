package dev2426.itsprojectwork.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev2426.itsprojectwork.Models.candidature;
import dev2426.itsprojectwork.Repository.candidatureRepository;

@Service
public class candidatureService {
	@Autowired
	private candidatureRepository repository;
	
	public List<candidature> getAll(){
		return repository.findAll();
	}
	
	public Optional<candidature> getOne(Long id) {
		return repository.findById(id);
	}
	
	public void insertOne(candidature nuovo) {
		repository.save(nuovo);
	}
	
	public void deleteOne(Long id) {
		repository.deleteById(id);
	}
}
