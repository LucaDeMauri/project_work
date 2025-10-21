package dev2426.itsprojectwork.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.ui.Model;

import dev2426.itsprojectwork.dto.UtenteDTO;
import dev2426.itsprojectwork.models.Utente;
import dev2426.itsprojectwork.repository.UtenteRepository;
import dev2426.itsprojectwork.services.AuthService;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private AuthService authService;
    

	@GetMapping("/login")
	public String loginPage(@RequestParam(defaultValue = "false") Boolean error, Model model) {   
			
		 if(error) {
			 model.addAttribute("message", "email o password errata");
		 }
		return "/Auth/login";
	}
	
	@PostMapping("/login")
	public String loginPage(@RequestParam String email,
            			    @RequestParam String password,
            				HttpSession session) {
		 
		UtenteDTO utente = authService.login(email, password);
		
		if(utente != null) {
			session.setAttribute("utenteLoggato", utente);
			return "redirect:/internship/";
			
        } else {
            return "redirect:/auth/login?error=true";
            
        }
		
	}
	
	
	@GetMapping("/signup")
	public String signupPage(@RequestParam(defaultValue = "false") Boolean error, @RequestParam(defaultValue = "false") Boolean registered,  Model model) {
		
		if(registered) {
			model.addAttribute("message", "Registrazione completata!");
		}
		if(error) {
			model.addAttribute("message", "Email già in uso");
		}
		return "/auth/signup";
	}

	@PostMapping("/signup")
	public String signupPage(@RequestParam String nome, @RequestParam String cognome, @RequestParam String email, @RequestParam String password) {
	
		UtenteDTO utente = authService.signUp(nome, cognome, email, password);
		
		if(utente != null) {
			return "redirect:/auth/signup?registered=true";
			
        } else {
            return "redirect:/auth/signup?error=true";
            
        }
	
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "/auth/login";
	}

}
