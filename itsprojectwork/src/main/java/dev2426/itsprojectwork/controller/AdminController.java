package dev2426.itsprojectwork.controller;

import dev2426.itsprojectwork.models.Ruolo;
import dev2426.itsprojectwork.models.StatoCandidatura;
import dev2426.itsprojectwork.models.Utente;
import dev2426.itsprojectwork.services.AnnunciService;
import dev2426.itsprojectwork.services.AziendeService;
import dev2426.itsprojectwork.services.CandidatureService;
import dev2426.itsprojectwork.services.CompetenzeService;
import dev2426.itsprojectwork.services.UtenteService;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute; // Aggiunto import
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/")
public class AdminController {
	@Autowired
	AnnunciService servizioAnnunci;
	
	@Autowired
	AziendeService servizioAzienda;
	
	@Autowired
	CandidatureService servizioCandidature;
	
	@Autowired
	CompetenzeService servizioCompetenze;
	
	@Autowired
	UtenteService servizioUtente;


    @ModelAttribute
    public void addCommonAttributes(Model model) {
        model.addAttribute("listaAnnunci",servizioAnnunci.getAll());
        model.addAttribute("elencoUtenti",servizioUtente.getAll());
        model.addAttribute("listaAziende", servizioAzienda.getAll());
        model.addAttribute("listaCandidature",servizioCandidature.getAll());
        model.addAttribute("listaCompetenze", servizioCompetenze.getAll());
    }

    @GetMapping("")
    public String adminDashboard(HttpSession session, Model model) {
        
        return "admin/dashboard";
    }
    
    @PostMapping("/disattiva/{id}")
    public String disattivaAnnuncio(@PathVariable Long id, RedirectAttributes ra) {
    	servizioAnnunci.deactivate(id); 
        ra.addFlashAttribute("message", "Annuncio disattivato con successo!");
        return "redirect:/admin/";
    }
    
    @GetMapping("/candidature")
    public String paginaCandidature(Model model) {
        // I dati comuni sono già stati aggiunti al Model dal metodo @ModelAttribute
        return "admin/candidature";
    }
    
    
    @PostMapping("/candidature/accetta/{id}")
    public String accettaCandidatura(@PathVariable Long id, RedirectAttributes ra) {
        try {
            servizioCandidature.updateStato(id, StatoCandidatura.ACCETTATO);
            ra.addFlashAttribute("message", "Candidatura ID " + id + " accettata con successo!");
        } catch (RuntimeException e) {
            ra.addFlashAttribute("message", "Errore: " + e.getMessage());
            ra.addFlashAttribute("error", true);
        }
        return "redirect:/admin/candidature";
    }


    @PostMapping("/candidature/rifiuta/{id}")
    public String rifiutaCandidatura(@PathVariable Long id, RedirectAttributes ra) {
        try {
            servizioCandidature.updateStato(id, StatoCandidatura.RIFIUTATO);
            ra.addFlashAttribute("message", "Candidatura ID " + id + " rifiutata.");
        } catch (RuntimeException e) {
            ra.addFlashAttribute("message", "Errore: " + e.getMessage());
            ra.addFlashAttribute("error", true);
        }
        return "redirect:/admin/candidature";
    }
}