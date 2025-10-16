package dev2426.itsprojectwork.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev2426.itsprojectwork.Models.annunci;
import dev2426.itsprojectwork.Repository.annunciRepository;

@Service
public class annunciService {

	@Autowired
	private annunciRepository repository;
	
	public List<annunci> getAll(){
		return repository.findAll();
	}
	
	public Optional<annunci> getOne(Long id) {
		return repository.findById(id);
	}
	
	public void insertOne(annunci nuovo) {
		repository.save(nuovo);
	}
	
	public void deleteOne(Long id) {
		repository.deleteById(id);
	}
}
