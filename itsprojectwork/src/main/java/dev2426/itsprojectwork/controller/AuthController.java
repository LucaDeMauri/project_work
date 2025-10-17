package dev2426.itsprojectwork.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@Autowired
	private UtenteRepository repository;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
    

	@GetMapping("/login")
	public String loginPage() {   
		return "/Auth/login";
	}
	
	@PostMapping("/login")
	public String loginPage(@RequestParam String email,
            				@RequestParam String password,
            				HttpSession session) {

		Optional<Utente> utenteOpt = repository.findByEmail(email);

        if (utenteOpt.isPresent()) {
            Utente utente = utenteOpt.get();

            if (passwordEncoder.matches(password, utente.getPassword())) {
                session.setAttribute("utenteLoggato", utente);
                return "redirect:/internship/";
            }
        }
        return "redirect:/Auth/login?error=true";
	}
	
	
	@GetMapping("/signup")
	public String signupPage() {
		
		
		return "/Auth/signup";
	}

	@PostMapping("/signup")
	public void signupPage(@RequestParam String nome, @RequestParam String cognome, @RequestParam String email, @RequestParam String password) {
	
		authService.signUp(nome, cognome, email, password);
		
			
	}
	
	

}
