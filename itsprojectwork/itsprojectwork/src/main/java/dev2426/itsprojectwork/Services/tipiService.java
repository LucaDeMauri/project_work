package dev2426.itsprojectwork.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev2426.itsprojectwork.Models.tipi;
import dev2426.itsprojectwork.Repository.tipiRepository;

@Service
public class tipiService {
	@Autowired
	private tipiRepository repository;
	
	public List<tipi> getAll(){
		return repository.findAll();
	}
	
	public Optional<tipi> getOne(Long id) {
		return repository.findById(id);
	}
	
	public void insertOne(tipi nuovo) {
		repository.save(nuovo);
	}
	
	public void deleteOne(Long id) {
		repository.deleteById(id);
	}
}
