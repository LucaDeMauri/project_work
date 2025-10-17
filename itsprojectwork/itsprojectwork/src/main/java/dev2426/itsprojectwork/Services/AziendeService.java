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
		return repository.findAll();
	}
	
	public Optional<Azienda> getOne(Long id) {
		return repository.findById(id);
	}
	
	public void insertOne(Azienda nuovo) {
		repository.save(nuovo);
	}
	
	public void deleteOne(Long id) {
		repository.deleteById(id);
	}
}
