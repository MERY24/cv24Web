package fr.univrouen.xmlProject.Controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api")
public class TestAcess {
    @GetMapping()
    public String hello() {
        return "Hello, world it working yaaay!";
    }
}
