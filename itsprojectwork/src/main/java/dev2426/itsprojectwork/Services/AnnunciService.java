package dev2426.itsprojectwork.Services;

import java.util.ArrayList;
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
	
	public ArrayList<Annuncio> getAll(){
		
		ArrayList<Annuncio> elenco = new ArrayList<>();
		
		for (Annuncio e :repository.findAll()) {
			elenco.add(e);
		}
		
		return elenco;
	}
	
	public Optional<Annuncio> getOne(Long id) {
		
		Optional<Annuncio> elemento = null;
		elemento = repository.findById(id);
		
		return elemento;
	}
	
	public void insertOne(Annuncio nuovo) {
		repository.save(nuovo);
	}
	
	public void deleteOne(Long id) {
		repository.deleteById(id);
	}
}
