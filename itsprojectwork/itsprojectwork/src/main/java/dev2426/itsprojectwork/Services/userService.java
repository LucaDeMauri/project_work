package dev2426.itsprojectwork.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev2426.itsprojectwork.Models.user;
import dev2426.itsprojectwork.Repository.userRepository;

@Service
public class userService {
	@Autowired
	private userRepository repository;
	
	public List<user> getAll(){
		return repository.findAll();
	}
	
	public Optional<user> getOne(Long id) {
		return repository.findById(id);
	}
	
	public void insertOne(user nuovo) {
		repository.save(nuovo);
	}
	
	public void deleteOne(Long id) {
		repository.deleteById(id);
	}
}
