package dev2426.itsprojectwork.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev2426.itsprojectwork.Models.ruoli;
import dev2426.itsprojectwork.Repository.ruoliRepository;


@Service
public class ruoliService {
	@Autowired
	private ruoliRepository repository;
	
	public List<ruoli> getAll(){
		return repository.findAll();
	}
	
	public Optional<ruoli> getOne(Long id) {
		return repository.findById(id);
	}
	
	public void insertOne(ruoli nuovo) {
		repository.save(nuovo);
	}
	
	public void deleteOne(Long id) {
		repository.deleteById(id);
	}
}
