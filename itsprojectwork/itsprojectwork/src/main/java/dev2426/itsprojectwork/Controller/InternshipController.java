package dev2426.itsprojectwork.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/intship/")
public class InternshipController {

    @GetMapping("dashboard")
    public String dashPage(){
        return "/Internship/Dashboard";
    }

}
