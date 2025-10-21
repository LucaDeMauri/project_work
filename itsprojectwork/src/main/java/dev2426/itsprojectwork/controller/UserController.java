package dev2426.itsprojectwork.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users/")
public class UserController {

    @GetMapping("lista")
    public String lista() {
    	
    	
    	
        return null;
    }

    @PostMapping("salva")
    public String salva() {
        return null;
    }

    
}
