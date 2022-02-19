package ru.javamentor.demospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class IndexController {
    @GetMapping("/person/admin")
    public String indexPage(){
        return "persons-table";
    }

    @GetMapping(value = "/new")
    public String newUser() {
        return "persons-new";
    }

    @GetMapping(value = "/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/person/one")
    public String personPage(@RequestParam long id, Model model){
        model.addAttribute("id", id);
        return "person-show";
    }

}
