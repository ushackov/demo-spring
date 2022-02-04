package ru.javamentor.demospring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.javamentor.demospring.model.Person;
import ru.javamentor.demospring.service.PersonService;

@Controller
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(value = "/person")
    public ModelAndView userInfo(@AuthenticationPrincipal Person person){
        final ModelAndView showModel = new ModelAndView("show");
        showModel.addObject("person", personService.findPerson(person.getId()));
        return showModel;
    }

    @GetMapping(value = "/person/{id}")
    public ModelAndView showUser(@PathVariable("id") long id){
        final ModelAndView showModelGet = new ModelAndView("show");
        showModelGet.addObject("person", personService.findPerson(id));
        return showModelGet;
    }


}
