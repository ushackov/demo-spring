package ru.javamentor.demospring.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.javamentor.demospring.model.Person;
import ru.javamentor.demospring.model.Role;
import ru.javamentor.demospring.service.PersonDetailsService;
import ru.javamentor.demospring.service.PersonService;

import java.util.HashSet;
import java.util.Set;

@RestController
public class AdminController {
    //
    private final PersonService personService;
    private final PersonDetailsService personDetails;

    public AdminController(PersonService personService, PersonDetailsService personDetails) {
        this.personService = personService;
        this.personDetails = personDetails;
    }

    //
    @PostMapping(value = "/admin/edit")
    public ModelAndView edit(@RequestBody Person person, @RequestParam String role) {
        final ModelAndView modelPersons = new ModelAndView("edit");
        if (person.getName() != "" && person.getSurname() != "" && person.getAge() != null &&
                person.getPassword() != "" && person.getUsername() != "" &&
                (personDetails.loadUserByUsername(person.getUsername()) == null ||
                        personService.findPerson(person.getId()).getUsername().equals(person.getUsername()))) {
            final Set<Role> roles = new HashSet<>();
            roles.add(personService.getRole((long) 1));
            person.setRoles(roles);

            if (role.equals("1")) {
                person.getRoles().add(personService.getRole((long) 1));
                person.getRoles().add(personService.getRole((long) 2));
            } else {
                person.getRoles().remove(personService.getRole((long) 1));
                person.getRoles().add(personService.getRole((long) 2));
            }

            personService.edit(person);

        } else {
            String s = "fuck you mf, it is not friendly exp";
            modelPersons.addObject("message", s);
            return modelPersons;
        }
        modelPersons.setViewName("redirect:/admin");
        return modelPersons;
    }

    @GetMapping(value = "/admin/find")
    public Person getPerson(@RequestParam long id) {
        return personService.findPerson(id);
    }

    @GetMapping(value = "/admin/delete")
    public void deleteUser(@RequestParam long id) {
        personService.delete(id);
    }
}
