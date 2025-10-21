// dev2426.itsprojectwork.Services.AuthService.java
package dev2426.itsprojectwork.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dev2426.itsprojectwork.dto.UtenteDTO;
import dev2426.itsprojectwork.models.Ruolo;
import dev2426.itsprojectwork.models.Utente;
import dev2426.itsprojectwork.repository.UtenteRepository;
import jakarta.servlet.http.HttpSession;

@Service
public class AuthService {

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public UtenteDTO login(String email, String rawPassword) {
        Optional<Utente> opt = utenteRepository.findByEmail(email);
        if (opt.isPresent()) {
        Utente u = opt.get();
            if (passwordEncoder.matches(rawPassword, u.getPassword())) {
            	
            	UtenteDTO nuovoDTO = new UtenteDTO();
                nuovoDTO.setId(u.getId());
                nuovoDTO.setNome(u.getNome());
                nuovoDTO.setCognome(u.getCognome());
                nuovoDTO.setEmail(u.getEmail());
            	
                return nuovoDTO;
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

        utenteRepository.save(nuovo);
        
        UtenteDTO nuovoDTO = new UtenteDTO();
        nuovoDTO.setId(nuovo.getId());
        nuovoDTO.setNome(nuovo.getNome());
        nuovoDTO.setCognome(nuovo.getCognome());
        nuovoDTO.setEmail(nuovo.getEmail());
        
        return nuovoDTO;
    }

    
   
    public void logout(HttpSession session) {
        session.invalidate();
    }


}
