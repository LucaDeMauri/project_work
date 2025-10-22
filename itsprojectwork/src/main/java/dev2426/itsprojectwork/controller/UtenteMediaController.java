package dev2426.itsprojectwork.controller;

import dev2426.itsprojectwork.dto.UtenteDTO;
import dev2426.itsprojectwork.services.AvatarFsService;
import dev2426.itsprojectwork.services.UtenteService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/utente")
public class UtenteMediaController {

    private final AvatarFsService avatarFsService;
    private final UtenteService utenteService;

    public UtenteMediaController(AvatarFsService avatarFsService, UtenteService utenteService) {
        this.avatarFsService = avatarFsService;
        this.utenteService = utenteService;
    }

    @PostMapping("/{id}/avatar")
    public String upload(@PathVariable Long id,
                         @RequestParam("file") MultipartFile file,
                         HttpSession session,
                         RedirectAttributes redirect) {
        UtenteDTO u = (UtenteDTO) session.getAttribute("utenteLoggato");
        if (u == null || !(u.getId() == id)) {
            redirect.addFlashAttribute("messaggioErrore", "Non autorizzato.");
            return "redirect:/auth/login";
        }
        try {
            avatarFsService.uploadAvatar(id, file);

            // ricarico l’utente aggiornato in sessione
            UtenteDTO refreshed = utenteService.getOne(id);
            session.setAttribute("utenteLoggato", refreshed);

            redirect.addFlashAttribute("messaggioSuccesso", "Immagine profilo aggiornata.");
        } catch (IllegalArgumentException ex) {
            redirect.addFlashAttribute("messaggioErrore", ex.getMessage());
        } catch (Exception ex) {
            redirect.addFlashAttribute("messaggioErrore", "Errore durante il salvataggio.");
        }
        return "redirect:/internship/areapersonale";
    }

    @PostMapping("/{id}/avatar/delete")
    public String delete(@PathVariable Long id,
                         HttpSession session,
                         RedirectAttributes redirect) {
        UtenteDTO u = (UtenteDTO) session.getAttribute("utenteLoggato");
        if (u == null || !(u.getId() == id)) {
            redirect.addFlashAttribute("messaggioErrore", "Non autorizzato.");
            return "redirect:/auth/login";
        }
        try {
            avatarFsService.deleteAvatar(id);

            // ricarico l’utente aggiornato in sessione
            UtenteDTO refreshed = utenteService.getOne(id);
            session.setAttribute("utenteLoggato", refreshed);

            redirect.addFlashAttribute("messaggioSuccesso", "Immagine profilo rimossa.");
        } catch (Exception ex) {
            redirect.addFlashAttribute("messaggioErrore", "Errore durante l'operazione.");
        }
        return "redirect:/internship/areapersonale";
    }
}
