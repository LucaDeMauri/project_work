package dev2426.itsprojectwork.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev2426.itsprojectwork.Models.aziende;
import dev2426.itsprojectwork.Repository.aziendeRepository;


@Service
public class aziendeService {
	@Autowired
	private aziendeRepository repository;
	
	public List<aziende> getAll(){
		return repository.findAll();
	}
	
	public Optional<aziende> getOne(Long id) {
		return repository.findById(id);
	}
	
	public void insertOne(aziende nuovo) {
		repository.save(nuovo);
	}
	
	public void deleteOne(Long id) {
		repository.deleteById(id);
	}
}
