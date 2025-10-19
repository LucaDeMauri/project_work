package dev2426.itsprojectwork.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dev2426.itsprojectwork.models.Ruolo;
import dev2426.itsprojectwork.models.Utente;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	 @GetMapping("")
	 public String adminPage(HttpSession session) {
		 
		 Utente utenteLoggato = (Utente) session.getAttribute("utenteLoggato");
		 
		 System.out.println(utenteLoggato.getRuolo());
		 System.out.println(utenteLoggato.getRuolo().equals(Ruolo.admin));
		 
		 if(utenteLoggato.getRuolo().equals(Ruolo.admin)){
			 return "/internship/admin";
		 }
		 
		 return "/internship/";
		 
	 }
	

}
