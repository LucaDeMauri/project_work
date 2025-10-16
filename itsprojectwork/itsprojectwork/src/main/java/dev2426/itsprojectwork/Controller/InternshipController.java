package dev2426.itsprojectwork.Controller;

import org.springframework.web.bind.annotation.GetMapping;

public class InternshipController {

	@GetMapping("")
	public String ciao() {
		return "ciao";
	}
}
