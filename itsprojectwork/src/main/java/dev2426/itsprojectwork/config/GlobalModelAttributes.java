package dev2426.itsprojectwork.config;

import dev2426.itsprojectwork.dto.UtenteDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;

@ControllerAdvice
@Component
public class GlobalModelAttributes {

    @ModelAttribute
    public void addSessionUser(Model model, HttpSession session) {
        UtenteDTO utente = (UtenteDTO) session.getAttribute("utenteLoggato");
        model.addAttribute("utente", utente);

        String avatarUrl = (utente != null && utente.getImmagine() != null && !utente.getImmagine().isBlank())
                ? utente.getImmagine()           // es: /media/avatars/1/avatar.jpg
                : "/img/avatar-default.png";     // fallback statico

        model.addAttribute("avatarUrl", avatarUrl);
    }
}
