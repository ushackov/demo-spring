package ru.javamentor.demospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.javamentor.demospring.model.Person;
import ru.javamentor.demospring.service.PersonDetailsService;
import ru.javamentor.demospring.service.PersonService;

import java.util.Collections;
import java.util.HashSet;

@RestController
public class RegistrationController {

    private final PersonService personService;
    private final PersonDetailsService personDetails;

    public RegistrationController(PersonService personService, PersonDetailsService personDetails) {
        this.personService = personService;
        this.personDetails = personDetails;
    }

    @GetMapping(value = "/")
    public ModelAndView printWelcome() {
        return new ModelAndView("redirect:login");
    }

    @PostMapping(value = "/")
    public ModelAndView create(@RequestBody Person person){
        final ModelAndView newPersonModel = new ModelAndView("persons-new");

        if (person.getName() != "" && person.getSurname() != "" && person.getAge() != null &&
                person.getPassword() != "" && person.getUsername() != "" &&
                personDetails.loadUserByUsername(person.getUsername()) == null) {

            person.setRoles(new HashSet<>(Collections.singleton(personService.getRole((long) 2))));
            personService.add(person);
            newPersonModel.setViewName("redirect:/login");

        } else {
            String s = "You entered incorrect data";
            newPersonModel.addObject("message", s);
        }
        return newPersonModel;
    }
}
