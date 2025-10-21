package dev2426.itsprojectwork.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dev2426.itsprojectwork.dto.UtenteDTO;
import dev2426.itsprojectwork.dto.UtentePasswordDTO;
import dev2426.itsprojectwork.services.CandidatureService;
import dev2426.itsprojectwork.services.UtenteService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/users/")
public class UserController {

	@Autowired
	UtenteService userService;
	
	@Autowired
	CandidatureService jobService;
	
    @GetMapping("lista")
    public String lista(HttpSession session, Model modelloDB) {
    	
    	
    	UtenteDTO utente = (UtenteDTO) session.getAttribute("utenteLoggato");
    	
    	modelloDB.addAttribute("candidature", jobService.getCandidatureUtente(utente));
        return "user/candidature";
    }

    @PostMapping("salva")
    public String salva() {
        return null;
    }

    
}
