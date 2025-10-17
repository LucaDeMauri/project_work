package dev2426.itsprojectwork.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dev2426.itsprojectwork.Services.AnnunciService;

@Controller
@RequestMapping("/admin/")
public class AdminController {
	
	@Autowired
	AnnunciService servizioAnnunci;
	
	@GetMapping("home")
	public String homePage(Model modelloDB) {
		
		modelloDB.addAttribute("listaAnnunci", servizioAnnunci.getAll());
		
		return "Admin/amministratore";
	}

}
