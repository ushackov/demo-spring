package ru.javamentor.demospring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.javamentor.demospring.dto.PersonDto;
import ru.javamentor.demospring.model.AdminRoleRequest;
import ru.javamentor.demospring.model.Person;
import ru.javamentor.demospring.service.DefaultEmailService;
import ru.javamentor.demospring.service.MappingService;
import ru.javamentor.demospring.service.PersonService;
import ru.javamentor.demospring.service.RequestService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PersonController {

    private final RequestService requestService;
    private final PersonService personService;
    private final MappingService mappingService;


    public PersonController(PersonService personService, RequestService requestService, MappingService mappingService) {
        this.personService = personService;
        this.requestService = requestService;
        this.mappingService = mappingService;
    }

    @GetMapping("persons/all")
    public List<Person> personsAll(){
        return personService.allPersons();
    }

    @GetMapping("persons/show")
    public PersonDto personOne(){
        return mappingService.mapToPersonDto(personService.getCurrentUsername());
    }

    @GetMapping("/persons/admin-role")
    public void adminRole(@RequestParam long id){
        requestService.save(new AdminRoleRequest(id));
    }
}
