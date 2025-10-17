package dev2426.itsprojectwork.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev2426.itsprojectwork.Models.Ruolo;
import dev2426.itsprojectwork.Repository.RuoliRepository;


@Service
public class RuoliService {
	@Autowired
	private RuoliRepository repository;
	
	public List<Ruolo> getAll(){
		return repository.findAll();
	}
	
	public Optional<Ruolo> getOne(Long id) {
		return repository.findById(id);
	}
	
	public void insertOne(Ruolo nuovo) {
		repository.save(nuovo);
	}
	
	public void deleteOne(Long id) {
		repository.deleteById(id);
	}
}
