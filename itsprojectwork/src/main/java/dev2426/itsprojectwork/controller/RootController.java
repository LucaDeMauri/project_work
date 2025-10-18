package dev2426.itsprojectwork.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dev2426.itsprojectwork.services.AnnunciService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class RootController {

	@Autowired
	private AnnunciService servizioAnnunci;
	
	@GetMapping("")
    public String sessionControl(HttpSession session) {
        Object utente = session.getAttribute("utenteLoggato");

        if (utente == null) {
            return "redirect:/auth/login";
        }

        return "redirect:/internship/dashboard";
    }
	
}
