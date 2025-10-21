package dev2426.itsprojectwork.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dev2426.itsprojectwork.dto.CandidaturaDTO;
import dev2426.itsprojectwork.dto.UtenteDTO;
import dev2426.itsprojectwork.services.CandidatureService;
import dev2426.itsprojectwork.services.UtenteService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    UtenteService userService;
    
    @Autowired
    CandidatureService jobService;
    
    // --- Visualizza Candidature Attive (Endpoint Esistente) ---
    @GetMapping("/lista")
    public String lista(HttpSession session, Model modelloDB) {
        
        UtenteDTO utente = (UtenteDTO) session.getAttribute("utenteLoggato");
        
        if (utente == null) {
            // Gestione di un utente non loggato
            return "redirect:/auth/login"; 
        }
        
        // Ho modificato la chiamata in base al servizio CandidatureService aggiornato
        List<CandidaturaDTO> candidature = jobService.getActiveCandidaturesByUtente(utente.getId());
        
        modelloDB.addAttribute("candidature", candidature);
        modelloDB.addAttribute("utente", utente); // Passa l'utente per l'header del profilo
        
        return "user/candidature";
    }

    // --- Visualizza la pagina di Modifica Profilo ---
    @GetMapping("/profilo/modifica")
    public String modificaProfilo(HttpSession session, Model model) {
        UtenteDTO utenteLoggato = (UtenteDTO) session.getAttribute("utenteLoggato");
        
        if (utenteLoggato == null) {
            // L'utente non è loggato
            return "redirect:/auth/login"; 
        }
        
        // Passa l'oggetto utente loggato al form Thymeleaf per pre-popolare i campi
        model.addAttribute("utenteDTO", utenteLoggato);
        
        return "user/modifica-profilo";
    }

    // --- Elabora il salvataggio delle modifiche ---
//    @PostMapping("/salva")
//    public String salvaModifiche(@ModelAttribute UtenteDTO utenteDTO, 
//                                 HttpSession session,
//                                 RedirectAttributes redirectAttributes) {
//        
//        UtenteDTO utenteLoggato = (UtenteDTO) session.getAttribute("utenteLoggato");
//        
//        if (utenteLoggato == null) {
//            return "redirect:/auth/login"; 
//        }
//
//        // Il DTO ricevuto (utenteDTO) contiene i nuovi dati. 
//        // Dobbiamo conservare l'ID dell'utente loggato.
//        utenteDTO.setId(utenteLoggato.getId());
//        
//        // 1. Logica di Business: Salva l'utente aggiornato
//        // Assumiamo che userService.updateProfile(utenteDTO) esista e restituisca l'UtenteDTO aggiornato
//        UtenteDTO utenteAggiornato = userService.updateProfile(utenteDTO);
//        
//        // 2. Aggiorna la sessione con i nuovi dati
//        session.setAttribute("utenteLoggato", utenteAggiornato);
//        
//        // 3. Aggiungi un messaggio di successo da mostrare dopo il redirect
//        redirectAttributes.addFlashAttribute("message", "Profilo aggiornato con successo!");
//        
//        // Reindirizza alla dashboard o alla pagina di modifica stessa
//        return "redirect:/users/profilo/modifica";
//    }
}