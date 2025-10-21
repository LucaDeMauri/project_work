package dev2426.itsprojectwork.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dev2426.itsprojectwork.dto.UtenteDTO;
import dev2426.itsprojectwork.services.AnnunciService;
import dev2426.itsprojectwork.services.CandidatureService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/internship")
public class InternshipController {
	
	@Autowired
	private AnnunciService servizioAnnunci;
	
	@Autowired
	private CandidatureService candidatureService;	
	
	@GetMapping("/")
    public String sessionControl(HttpSession session) {
        UtenteDTO utente = (UtenteDTO) session.getAttribute("utenteLoggato");

        if (utente == null) {
            return "redirect:/auth/login";
        }

        return "redirect:/internship/dashboard";
    }
	
	@GetMapping("/dashboard")
	public String dashboard(Model modelloDB) {
		
		modelloDB.addAttribute("listaAnnunci", servizioAnnunci.getAll());
		
		return "internship/dashboard";
	}
	
	@PostMapping("/dashboard")
	public String getCandidaturaUtente(@RequestParam Long id, HttpSession session ) {
	
		UtenteDTO utente = (UtenteDTO) session.getAttribute("utenteLoggato");
		candidatureService.insertOne(id, utente.getId());
		
		return "redirect:/internship/dashboard";
		
		
	}
}
