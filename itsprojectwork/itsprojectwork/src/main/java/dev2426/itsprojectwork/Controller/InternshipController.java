package dev2426.itsprojectwork.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dev2426.itsprojectwork.Services.AnnunciService;

@Controller
@RequestMapping("/internship/")
public class InternshipController {
	
	@Autowired
	private AnnunciService servizioAnnunci;
	
	@GetMapping("dashboard")
	public String dashboard(Model modelloDB) {
		
		modelloDB.addAttribute("listaAnnunci", servizioAnnunci.getAll());
		
		return "Internship/Dashboard";
	}
}
