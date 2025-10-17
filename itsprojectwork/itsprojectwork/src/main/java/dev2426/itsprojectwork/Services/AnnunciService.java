package dev2426.itsprojectwork.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev2426.itsprojectwork.Models.Annuncio;
import dev2426.itsprojectwork.Repository.AnnunciRepository;

@Service
public class AnnunciService {

	@Autowired
	private AnnunciRepository repository;
	
	public List<Annuncio> getAll(){
		return repository.findAll();
	}
	
	public Optional<Annuncio> getOne(Long id) {
		return repository.findById(id);
	}
	
	public void insertOne(Annuncio nuovo) {
		repository.save(nuovo);
	}
	
	public void deleteOne(Long id) {
		repository.deleteById(id);
	}
}
