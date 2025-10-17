package dev2426.itsprojectwork.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping("/auth/")
public class AuthController {

	@GetMapping("login")
	public String loginPage() {
		return "/Auth/login";
	}
	
	@GetMapping("signup")
	public String signupPage() {
		return null;
	}
}
