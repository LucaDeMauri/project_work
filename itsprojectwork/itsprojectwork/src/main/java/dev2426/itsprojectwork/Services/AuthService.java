package dev2426.itsprojectwork.Services;

import java.util.Optional;
import java.util.UUID;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev2426.itsprojectwork.Models.Utente;
import dev2426.itsprojectwork.Repository.UtenteRepository;


@Service
public class AuthService {

	
	@Autowired
	private UtenteRepository utenteRepository;
	
	private String login(String email, String password) {
		
		Optional<Utente> utenteOpt = utenteRepository.findByEmail(email);
		
		if (utenteOpt.isPresent() && utenteOpt.get().getPassword().equals(password)) {
            Utente utente = utenteOpt.get();

            String token = UUID.randomUUID().toString();

            utente.setToken(token);
            utenteRepository.save(utente);

            return token; 
        }
        return null;
    }

    public Utente validaToken(String token) {
        return utenteRepository.findByToken(token).orElse(null);
    }
		
		
}
