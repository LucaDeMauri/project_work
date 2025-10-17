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
		return repository.findAll();
	}
	
	public Optional<Tipo> getOne(Long id) {
		return repository.findById(id);
	}
	
	public void insertOne(Tipo nuovo) {
		repository.save(nuovo);
	}
	
	public void deleteOne(Long id) {
		repository.deleteById(id);
	}
}
