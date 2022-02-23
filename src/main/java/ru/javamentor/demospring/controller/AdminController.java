package ru.javamentor.demospring.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.javamentor.demospring.model.AdminRoleRequest;
import ru.javamentor.demospring.model.Person;
import ru.javamentor.demospring.service.PersonService;
import ru.javamentor.demospring.service.RequestService;

import java.util.List;

@RestController
public class AdminController {
    //
    private final PersonService personService;
    private final RequestService requestService;

    public AdminController(PersonService personService, RequestService requestService) {
        this.personService = personService;
        this.requestService = requestService;
    }

    @PostMapping(value = "/admin/edit")
    public ResponseEntity<String> edit(@RequestBody Person person, @RequestParam String role) {
        if(personService.edit(person, role)){
            return ResponseEntity.ok("Person edited");
        } else {
            String s = "fuck you mf, it is not friendly exp";
            return ResponseEntity.ok(s);
        }
    }

    @GetMapping(value = "/admin/find")
    public Person getPerson(@RequestParam long id) {
        return personService.findPerson(id);
    }

    @GetMapping(value = "/admin/delete")
    public void deleteUser(@RequestParam long id) {
        personService.delete(id);
    }

    @GetMapping(value = "/admin/this")
    public Person getAuthPerson() {
        return personService.getCurrentUsername();
    }

    @GetMapping(value = "/admin/requests")
    public List<AdminRoleRequest> requests(){
        return requestService.findAll();
    }
}
