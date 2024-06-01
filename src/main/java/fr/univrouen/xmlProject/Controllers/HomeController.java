package fr.univrouen.xmlProject.Controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {



    @GetMapping("/")
    public String getHomePage(Model model) {
        model.addAttribute("projectName", "Cv Manager 2024");
        model.addAttribute("version", "V1");
        model.addAttribute("teamMember", "TOUATI Meriem Yasmine");
        model.addAttribute("universityLogo", "/images/Université_de_Rouen.png");

        return "home";
    }

    @GetMapping("/help")
    public String getHelpPage() {
        return "help";
    }
}
