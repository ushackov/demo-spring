package ru.javamentor.demospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.javamentor.demospring.model.Person;
import ru.javamentor.demospring.service.PersonDetailsService;
import ru.javamentor.demospring.service.PersonService;

import java.util.Collections;
import java.util.HashSet;

@Controller
public class RegistrationController {

    private final PersonService personService;
    private final PersonDetailsService personDetails;

    public RegistrationController(PersonService personService, PersonDetailsService personDetails) {
        this.personService = personService;
        this.personDetails = personDetails;
    }

    @GetMapping(value = "/")
    public ModelAndView printWelcome() {
        return new ModelAndView("index");
    }

    @PostMapping(value = "/")
    public ModelAndView create(@ModelAttribute("person") Person person) {
        final ModelAndView newPersonModel = new ModelAndView("new");
        if (person.getName() != "" && person.getSurname() != "" && person.getAge() != null &&
                person.getPassword() != "" && person.getUsername() != "" &&
                personDetails.loadUserByUsername(person.getUsername()) == null) {

            person.setRoles(new HashSet<>(Collections.singleton(personService.getRole((long) 2))));
            personService.add(person);
            newPersonModel.setViewName("redirect:/login");
            return newPersonModel;

        } else {
            String s = "You entered incorrect data";
            newPersonModel.addObject("message", s);
            return newPersonModel;
        }
    }

    @GetMapping(value = "/new")
    public ModelAndView newUser(@ModelAttribute("person") Person person) {
        return new ModelAndView("new");
    }

    @GetMapping(value = "/login")
    public ModelAndView loginPage() {
        return new ModelAndView("login");
    }
}
