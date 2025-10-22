package dev2426.itsprojectwork.controller;

import dev2426.itsprojectwork.models.Ruolo;
import dev2426.itsprojectwork.models.Utente;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/dashboard")
    public String adminDashboard(HttpSession session, Model model) {
        Utente utente = (Utente) session.getAttribute("utenteLoggato");

        if (utente == null) {
            return "redirect:/auth/login";
        }

        if (!utente.getRuolo().equals(Ruolo.admin)) {
            return "redirect:/internship/dashboard";
        }

        model.addAttribute("utente", utente);
        return "admin/dashboard";
    }
}
