package dev2426.itsprojectwork.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import dev2426.itsprojectwork.dto.UtenteDTO;
import dev2426.itsprojectwork.models.Ruolo;
import dev2426.itsprojectwork.services.AuthService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth")
public class AuthController {
    
    // Regex per Nome: Consente lettere, spazi e punti.
    private static final String NOME_REGEX = "^[\\p{L} .]+$"; 
    // Regex per Cognome: Consente lettere, spazi, punti e apostrofi.
    private static final String COGNOME_REGEX = "^[\\p{L} '.\\-]+$";

    @Autowired
    private AuthService authService;

    private boolean isValidNameFormat(String value, String regex) {
        if (value == null || value.isBlank()) return false;
        return value.matches(regex);
    }

    // --- LOGIN ---
	@GetMapping("/login")
	public String loginPage(@RequestParam(defaultValue = "false") Boolean registered, 
                            Model model) {	
        if(registered) {
            model.addAttribute("message", "Registrazione completata! Puoi accedere ora.");
            model.addAttribute("alertType", "success");
        }
        // Il percorso del template è in /Auth/login, ma i percorsi sono case-sensitive. Lo mantengo come nel tuo codice.
        return "/Auth/login";
	}
	
	@PostMapping("/login")
	public String processLogin(@RequestParam String email,
							@RequestParam String password,
                            Model model, 
							HttpSession session) {

        if (email == null || email.isBlank() || password == null || password.isBlank()) {
            model.addAttribute("message", "Compila tutti i campi obbligatori.");
            model.addAttribute("alertType", "error");
            return "/Auth/login";
        }
		
		UtenteDTO utente = authService.login(email, password);
		
		if(utente != null) {
			session.setAttribute("utenteLoggato", utente);
			
            // CORREZIONE CHIAVE (Linea 56 originale): Aggiunto controllo null
            // Verifichiamo prima se il ruolo è admin. Se nullo, va nel ramo else (utente standard)
			if(utente.getRuolo() != null && utente.getRuolo() == Ruolo.admin) {
				return "redirect:/admin/";
			}
			else {
				return "redirect:/internship/";
			}
		} else {
            model.addAttribute("message", "Email o password errata.");
            model.addAttribute("alertType", "error");
            return "/Auth/login";
		}
	}
    
    // --- SIGNUP ---
	@GetMapping("/signup")
	public String signupPage(Model model) {
		return "/Auth/signup";
	}

	@PostMapping("/signup")
	public String processSignup(@RequestParam(required = false) String nome, 
                                @RequestParam(required = false) String cognome, 
                                @RequestParam(required = false) String email,
                                @RequestParam(required = false) String password, 
                                @RequestParam(required = false) String confirmPassword, 
                                Model model) {

        // Ripopolamento dei campi in caso di errore
        model.addAttribute("nome", nome);
        model.addAttribute("cognome", cognome);
        model.addAttribute("email", email);

        // 1. Validazione Campi Vuoti (Manuale)
        if (nome == null || nome.isBlank() || cognome == null || cognome.isBlank() || 
            email == null || email.isBlank() || password == null || password.isBlank() || 
            confirmPassword == null || confirmPassword.isBlank()) {
            
            model.addAttribute("message", "Tutti i campi sono obbligatori.");
            model.addAttribute("alertType", "error");
            return "/Auth/signup";
        }
        
        // 2. Validazione Caratteri (Backend - Ultima Sicurezza)
        if (!isValidNameFormat(nome, NOME_REGEX) || !isValidNameFormat(cognome, COGNOME_REGEX)) {
            model.addAttribute("message", "Nome o Cognome contengono caratteri non validi.");
            model.addAttribute("alertType", "error");
            return "/Auth/signup";
        }

        // 3. Validazione Password (Backend - Ultima sicurezza)
        if (!password.equals(confirmPassword)) {
            model.addAttribute("message", "Le password non coincidono.");
            model.addAttribute("alertType", "error");
            return "/Auth/signup";
        }
        
        // 4. Controllo Email (Business Logic)
		UtenteDTO utente = authService.signUp(nome, cognome, email, password);

		if (utente != null) {
			return "redirect:/auth/login?registered=true";
		} else {
			// ERRORE: Email già presente nel DB
			model.addAttribute("message", "Email già in uso. Prova il login.");
			model.addAttribute("alertType", "error");
			return "/Auth/signup";
		}
	}

	// --- LOGOUT ---
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/auth/login";
	}
}