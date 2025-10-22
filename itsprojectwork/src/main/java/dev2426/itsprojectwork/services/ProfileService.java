// dev2426.itsprojectwork.services.ProfileService.java
package dev2426.itsprojectwork.services;

import dev2426.itsprojectwork.dto.UpdateProfileFormDTO;
import dev2426.itsprojectwork.models.Utente;
import dev2426.itsprojectwork.repository.UtenteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProfileService {

    private final UtenteRepository utenteRepository;

    public ProfileService(UtenteRepository utenteRepository) {
        this.utenteRepository = utenteRepository;
    }

    @Transactional
    public Utente updateProfile(Long userId, UpdateProfileFormDTO form) {
        Utente u = utenteRepository.findById(userId).orElseThrow();
        u.setNome(form.getNome());
        u.setCognome(form.getCognome());
        u.setEmail(form.getEmail());
        u.setBio(form.getBio());
        return utenteRepository.save(u);
    }
}
