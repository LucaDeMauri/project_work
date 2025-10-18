package dev2426.itsprojectwork.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.ui.Model;
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
	public String loginPage(@RequestParam(defaultValue = "false") Boolean registered, @RequestParam(defaultValue = "false") Boolean error, Model model) {   
			
		 if(registered) {
			 model.addAttribute("message", 	"Registrazione completata!");
		 }
		 if(error) {
			 model.addAttribute("message", "email o password errata");
		 }
		return "/Auth/login";
	}
	
	@PostMapping("/login")
	public String loginPage(@RequestParam String email,
            			    @RequestParam String password,
            				HttpSession session) {
		
		Utente utente = authService.login(email, password);
		
		if(utente != null) {
			session.setAttribute("utenteLoggato", utente);
			return "redirect:/internship/";
			
        } else {
            return "redirect:/auth/login?error=true";
            
        }
		
	}
	
	
	@GetMapping("/signup")
	public String signupPage() {
		
		return "/auth/signup";
	}

	@PostMapping("/signup")
	public String signupPage(@RequestParam String nome, @RequestParam String cognome, @RequestParam String email, @RequestParam String password) {
	
		Utente utente = authService.signUp(nome, cognome, email, password);
		
		if(utente != null) {
			return "redirect:/auth/login?registered=true";
			
        } else {
            return "redirect:/auth/signup?error=true";
            
        }
		
			
	}
	
	

}
