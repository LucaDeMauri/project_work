// dev2426.itsprojectwork.Services.AuthService.java
package dev2426.itsprojectwork.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dev2426.itsprojectwork.models.Utente;
import dev2426.itsprojectwork.repository.UtenteRepository;
import jakarta.servlet.http.HttpSession;

@Service
public class AuthService {

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public String signUp(String nome, String cognome, String email, String password) {

        if (utenteRepository.existsByEmail(email)) {
            return "redirect:/register?error=emailInUso";
        }

        Utente nuovo = new Utente();
        nuovo.setNome(nome);
        nuovo.setCognome(cognome);
        nuovo.setEmail(email.trim().toLowerCase());
        nuovo.setPassword(passwordEncoder.encode(password));

        Utente salvato = utenteRepository.save(nuovo);


        return "redirect:/dashboard";
    }

    public void logout(HttpSession session) {
        session.invalidate();
    }


}
