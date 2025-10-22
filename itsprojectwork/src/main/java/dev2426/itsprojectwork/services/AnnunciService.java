package dev2426.itsprojectwork.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev2426.itsprojectwork.dto.AnnuncioDTO;
import dev2426.itsprojectwork.dto.AziendaDTO;
import dev2426.itsprojectwork.mapper.DtoMapper;
import dev2426.itsprojectwork.models.Annuncio;
import dev2426.itsprojectwork.repository.AnnunciRepository;
import jakarta.transaction.Transactional;

@Service
public class AnnunciService {

	@Autowired
	private AnnunciRepository repository;

	public List<AnnuncioDTO> getAll() {

		List<AnnuncioDTO> elenco = new ArrayList<>();

		for (Annuncio e : repository.findAll()) {
			elenco.add(DtoMapper.toAnnuncioDTO(e));

		}

		return elenco;
	}

	public AnnuncioDTO getOne(Long id) {

		Optional<Annuncio> annuncio = null;
		annuncio = repository.findById(id);

		Annuncio a = annuncio.get();
		return DtoMapper.toAnnuncioDTO(a);

	}

	public void insertOne(AnnuncioDTO nuovo) {
		
		Annuncio 
		
		repository.save(nuovo);
	}

	public void deleteOne(Long id) {
		repository.deleteById(id);
	}

	@Transactional
	public void deactivate(Long annuncioId) {
		Optional<Annuncio> annuncio = repository.findById(annuncioId);

		annuncio.get().setActive(false);
		repository.save(annuncio.get());
	}
}
