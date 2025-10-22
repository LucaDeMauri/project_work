package dev2426.itsprojectwork.controller;

import dev2426.itsprojectwork.dto.UtenteDTO;
import dev2426.itsprojectwork.services.AnnunciService;
import dev2426.itsprojectwork.services.CandidatureService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collections;

@Controller
@RequestMapping("/internship")
public class InternshipController {

    @Autowired
    private AnnunciService servizioAnnunci;

    @Autowired
    private CandidatureService candidatureService;

    @GetMapping("/")
    public String sessionControl(HttpSession session) {
        UtenteDTO utente = (UtenteDTO) session.getAttribute("utenteLoggato");
        if (utente == null) {
            return "redirect:/auth/login";
        }
        return "redirect:/internship/dashboard";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpSession session) {
        UtenteDTO utente = (UtenteDTO) session.getAttribute("utenteLoggato");
        model.addAttribute("listaAnnunci", servizioAnnunci.getAll());
        model.addAttribute("idsGiaCandidato",
                utente != null
                        ? candidatureService.idsAnnunciGiaCandidato(utente.getId())
                        : Collections.emptySet());
        return "internship/dashboard";
    }

    @PostMapping("/dashboard")
    public String getCandidaturaUtente(@RequestParam Long id,
                                       HttpSession session,
                                       RedirectAttributes redirect) {

        UtenteDTO utente = (UtenteDTO) session.getAttribute("utenteLoggato");
        if (utente == null) {
            redirect.addFlashAttribute("messaggioErrore", "Sessione scaduta. Accedi di nuovo.");
            return "redirect:/auth/login";
        }

        var esito = candidatureService.insertOne(id, utente.getId());

        switch (esito) {
            case DUPLICATA -> redirect.addFlashAttribute("messaggioErrore", "Ti sei già candidato a questo annuncio!");
            case INSERITA -> redirect.addFlashAttribute("messaggioSuccesso", "Candidatura inviata con successo!");
            case ANNUNCIO_NON_TROVATO -> redirect.addFlashAttribute("messaggioErrore", "Annuncio non trovato.");
            case ERRORE -> redirect.addFlashAttribute("messaggioErrore", "Si è verificato un errore inatteso.");
        }
        return "redirect:/internship/dashboard";
    }

    @GetMapping("/areapersonale")
    public String areaPersonale(Model model, HttpSession session, RedirectAttributes redirect) {
        UtenteDTO utente = (UtenteDTO) session.getAttribute("utenteLoggato");
        if (utente == null) {
            redirect.addFlashAttribute("messaggioErrore", "Sessione scaduta. Accedi di nuovo.");
            return "redirect:/auth/login";
        }

        // niente avatarUrl e niente utente qui: li inserisce GlobalModelAttributes
        var candidature = candidatureService.findForUserWithAnnunci(utente.getId());
        model.addAttribute("candidature", candidature);
        model.addAttribute("candidatureCount", candidature.size());

        return "internship/areapersonale";
    }
}
