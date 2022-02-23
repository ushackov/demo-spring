package ru.javamentor.demospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/person/admin")
    public String indexPage(){
        return "persons-table";
    }

    @GetMapping(value = "/new")
    public String newUser(Model model) {
        return "persons-new";
    }

    @GetMapping(value = "/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/person/one")
    public String personPage(){
        return "person-show";
    }
}
