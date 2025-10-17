package dev2426.itsprojectwork.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dev2426.itsprojectwork.services.AnnunciService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/internship")
public class InternshipController {
	
	@Autowired
	private AnnunciService servizioAnnunci;
	
	@GetMapping("/")
    public String sessionControl(HttpSession session) {
        Object utente = session.getAttribute("utenteLoggato");

        if (utente == null) {
            return "redirect:/auth/login";
        }

        return "redirect:/internship/dashboard";
    }
	
	@GetMapping("/dashboard")
	public String dashboard(Model modelloDB) {
		
		modelloDB.addAttribute("listaAnnunci", servizioAnnunci.getAll());
		
		return "Internship/Dashboard";
	}
}
