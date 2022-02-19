package ru.javamentor.demospring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.javamentor.demospring.model.Person;
import ru.javamentor.demospring.service.PersonService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("persons/all")
    public List<Person> personsAll(){
        return personService.allPersons();
    }

    @GetMapping("persons/show")
    public List<Person> personOne(@RequestParam long id){
        final ArrayList<Person> persons = new ArrayList<>();
        persons.add(personService.findPerson(id));
        return persons;
    }

}