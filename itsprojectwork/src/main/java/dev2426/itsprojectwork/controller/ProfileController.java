package dev2426.itsprojectwork.controller;

import dev2426.itsprojectwork.dto.UpdateProfileFormDTO;
import dev2426.itsprojectwork.dto.UtenteDTO;
import dev2426.itsprojectwork.models.Utente;
import dev2426.itsprojectwork.repository.UtenteRepository;
import dev2426.itsprojectwork.services.AvatarFsService;
import dev2426.itsprojectwork.services.ProfileService;
import dev2426.itsprojectwork.services.UtenteService;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/internship/modificaprofilo")
public class ProfileController {

    @Autowired
    private UtenteService utenteService;
    
    @Autowired
    private ProfileService profileService;
    
    @Autowired
    private AvatarFsService avatarFsService;



    @GetMapping
    public String edit(Model model, HttpSession session) {
        UtenteDTO utente = (UtenteDTO) session.getAttribute("utenteLoggato");
        if (utente == null) return "redirect:/auth/login";

        UtenteDTO u = utenteService.getOne(utente.getId());

        UpdateProfileFormDTO form = new UpdateProfileFormDTO();
        form.setNome(u.getNome());
        form.setCognome(u.getCognome());
        form.setEmail(u.getEmail());
        form.setBio(u.getBio());

        model.addAttribute("form", form);
        model.addAttribute("userImage", u.getImmagine());
        model.addAttribute("userNome", u.getNome());
        model.addAttribute("userCognome", u.getCognome());
        return "internship/modificaprofilo";
    }

    @PostMapping
    public String update(@ModelAttribute("form") UpdateProfileFormDTO form,
                         BindingResult binding,
                         @RequestParam(name = "immagine", required = false) MultipartFile immagine,
                         HttpSession session,
                         RedirectAttributes ra,
                         Model model) {

        UtenteDTO utente = (UtenteDTO) session.getAttribute("utenteLoggato");
        if (utente == null) return "redirect:/auth/login";
        Long userId = utente.getId();

        if (binding.hasErrors()) {
            UtenteDTO u = utenteService.getOne(userId);
            model.addAttribute("userImage", u.getImmagine());
            model.addAttribute("userNome", u.getNome());
            model.addAttribute("userCognome", u.getCognome());
            return "internship/modificaprofilo";
        }

        // 1) Aggiorna dati base
        profileService.updateProfile(userId, form);

        // 2) Gestione immagine
        try {
            if (Boolean.TRUE.equals(form.getRemoveImage())) {
                avatarFsService.deleteAvatar(userId);
            } else if (immagine != null && !immagine.isEmpty()) {
                avatarFsService.uploadAvatar(userId, immagine);
            }
        } catch (IllegalArgumentException ex) {
            ra.addFlashAttribute("error", ex.getMessage());
            return "redirect:/internship/modificaprofilo";
        } catch (Exception ex) {
            ra.addFlashAttribute("error", "Errore durante il salvataggio dell'immagine.");
            return "redirect:/internship/modificaprofilo";
        }

        // 3) (FACOLTATIVO ma raccomandato) ricarica l’utente aggiornato nella sessione
        UtenteDTO aggiornato = utenteService.getOne(userId);
        session.setAttribute("utenteLoggato", aggiornato);

        ra.addFlashAttribute("success", "Profilo aggiornato con successo.");
        return "redirect:/internship/modificaprofilo";
    }

}
