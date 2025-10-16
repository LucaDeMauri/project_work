package dev2426.itsprojectwork.Controller;

<<<<<<< HEAD
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class InternshipController {
	
	@GetMapping("")
	public String home() {
		return null;
		
	}
	
	
=======
import org.springframework.web.bind.annotation.GetMapping;

public class InternshipController {

	@GetMapping("")
	public String ciao() {
		return "ciao";
	}
>>>>>>> 892359ce12405ca71aab59830b285e9d049d8637
}
