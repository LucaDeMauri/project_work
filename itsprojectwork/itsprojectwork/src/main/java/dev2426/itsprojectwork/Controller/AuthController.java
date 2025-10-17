package dev2426.itsprojectwork.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dev2426.itsprojectwork.Services.AuthService;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping("/auth/")
public class AuthController {
	
	@Autowired
	private AuthService authService;

	@GetMapping("login")
	public String loginPage(@RequestParam String email,
				            @RequestParam String password,
				            HttpSession session) {   
		authService.login(email, password, session);
		return "/Auth/login";
	}
	
	@GetMapping("signup")
	public void signupPage(@RequestParam String email, @RequestParam String password) {
	
		authService.signUp(email, password);
			
	}
}
