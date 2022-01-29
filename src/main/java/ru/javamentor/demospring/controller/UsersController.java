package ru.javamentor.demospring.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.javamentor.demospring.service.WebService;

@RestController
@RequestMapping("/users")
public class UsersController {
    WebService service;

    public UsersController() {
    }

    public UsersController(WebService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ModelAndView showAll(){
        ModelAndView model = new ModelAndView("users");
        model.addObject("users", service.getAll());
        return model;
    }

    @GetMapping
    public ModelAndView showUser(@PathVariable("id")long id){
        final ModelAndView modelAndView = new ModelAndView("user");
        modelAndView.addObject("user", service.get(id));
        return modelAndView;
    }

    @PostMapping
    public ModelAndView edit(){
        final ModelAndView model = new ModelAndView();
        return model;
    }
}
