// dev2426.itsprojectwork.Services.AuthService.java
package dev2426.itsprojectwork.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import dev2426.itsprojectwork.dto.UtenteDTO;
import dev2426.itsprojectwork.models.Ruolo;
import dev2426.itsprojectwork.models.Utente;
import dev2426.itsprojectwork.repository.UtenteRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

@Service
public class AuthService {

    @Autowired
    private UtenteRepository utenteRepository;
    
    @Autowired
    private UtenteService utenteService;

    @Autowired
    private PasswordEncoder passwordEncoder;
        
    
    public UtenteDTO login(String email, String password) {

		Optional<Utente> utenteOpt = utenteRepository.findByEmail(email);
		
		if (utenteOpt.isPresent()) {
			Utente utente = utenteOpt.get();
			
			UtenteDTO userDTO = new UtenteDTO(utente.getImmagine(),utente.getBio(),utente.getNome(),utente.getCognome(),utente.getEmail(),utente.getRuolo());
			
			if (passwordEncoder.matches(password, utente.getPassword())) {
				return userDTO;
			}
		}
		
        return null;
    }
    
    
    
    public UtenteDTO signUp(String nome, String cognome, String email, String password) {

        if (utenteRepository.existsByEmail(email)) {
    		return null;
        }

        Utente nuovo = new Utente();
        nuovo.setNome(nome);
        nuovo.setCognome(cognome);
        nuovo.setEmail(email.trim().toLowerCase());
        nuovo.setPassword(passwordEncoder.encode(password));
        nuovo.setRuolo(Ruolo.user);

        Utente newUser = utenteRepository.save(nuovo);
        
        UtenteDTO userDTO = new UtenteDTO(newUser.getImmagine(),newUser.getBio(),newUser.getNome(),newUser.getCognome(),newUser.getEmail(),newUser.getRuolo());
        
        return userDTO;
    }

    
   
    public void logout(HttpSession session) {
        session.invalidate();
    }


}
