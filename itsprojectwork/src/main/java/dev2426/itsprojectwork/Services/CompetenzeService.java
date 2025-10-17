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
		return repository.findAll();
	}
	
	public Optional<Competenza> getOne(Long id) {
		return repository.findById(id);
	}
	
	public void insertOne(Competenza nuovo) {
		repository.save(nuovo);
	}
	
	public void deleteOne(Long id) {
		repository.deleteById(id);
	}
}
