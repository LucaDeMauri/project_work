package dev2426.itsprojectwork.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev2426.itsprojectwork.dto.CompSviluppateDTO;
import dev2426.itsprojectwork.repository.CompSviluppateRepository;

@Service
public class CompSviluppateService {

	@Autowired
	private CompSviluppateRepository compRep;
	
	public ArrayList<CompSviluppateDTO> elencoCompSviluppate(){
		return null;
		
	}
}
