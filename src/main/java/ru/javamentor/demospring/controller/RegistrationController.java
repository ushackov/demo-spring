package ru.javamentor.demospring.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import ru.javamentor.demospring.model.Person;
import ru.javamentor.demospring.service.PersonService;

@RestController
public class RegistrationController {

    private final PersonService personService;

    public RegistrationController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(value = "/")
    public ModelAndView printWelcome() {
        return new ModelAndView("redirect:login");
    }

    @PostMapping("/")
    public ResponseEntity<String> create(@RequestBody Person person) {
        if (personService.add(person)){
            return ResponseEntity.ok("THIS IS OK MF");
        }else return ResponseEntity.ok("THIS IS not OK");
    }
}
