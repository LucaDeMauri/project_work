package dev2426.itsprojectwork.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev2426.itsprojectwork.Models.competenze;
import dev2426.itsprojectwork.Repository.competenzeRepository;

@Service
public class competenzeService {

	@Autowired
	private competenzeRepository repository;
	
	public List<competenze> getAll(){
		return repository.findAll();
	}
	
	public Optional<competenze> getOne(Long id) {
		return repository.findById(id);
	}
	
	public void insertOne(competenze nuovo) {
		repository.save(nuovo);
	}
	
	public void deleteOne(Long id) {
		repository.deleteById(id);
	}
}
