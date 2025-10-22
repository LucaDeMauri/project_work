package dev2426.itsprojectwork.controller;

import dev2426.itsprojectwork.models.Ruolo;
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

    @GetMapping("")
    public String adminDashboard(HttpSession session, Model model) {
        model.addAttribute("listaAnnunci",servizioAnnunci.getAll());
        model.addAttribute("elencoUtenti",servizioUtente.getAll());
        model.addAttribute("listaAziende", servizioAzienda.getAll());
        model.addAttribute("listaCandidature",servizioCandidature.getAll());
        model.addAttribute("listaCompetenze", servizioCompetenze.getAll());
       
        return "admin/dashboard";
    }
    
    @PostMapping("/disattiva/{id}")
    public String disattivaAnnuncio(@PathVariable Long id, RedirectAttributes ra) {
    	servizioAnnunci.deactivate(id); 
        ra.addFlashAttribute("message", "Annuncio disattivato con successo!");
        return "redirect:/admin/";
    }
    
}
