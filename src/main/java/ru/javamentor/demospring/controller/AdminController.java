package ru.javamentor.demospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.javamentor.demospring.model.Person;
import ru.javamentor.demospring.model.Role;
import ru.javamentor.demospring.service.PersonDetailsService;
import ru.javamentor.demospring.service.PersonService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class AdminController {

    private final PersonService personService;
    private final PersonDetailsService personDetails;

    public AdminController(PersonService personService, PersonDetailsService personDetails) {
        this.personService = personService;
        this.personDetails = personDetails;
    }

    @PostMapping(value = "/admin")
    public ModelAndView create(@ModelAttribute("person") Person person,
                               @ModelAttribute("adminRole") String adminRole) {
        final ModelAndView modelPersons = new ModelAndView("edit");
        if (person.getName() != "" && person.getSurname() != "" && person.getAge() != null &&
                person.getPassword() != "" && person.getUsername() != "" &&
                (personDetails.loadUserByUsername(person.getUsername()) == null ||
                        personService.findPerson(person.getId()).getUsername().equals(person.getUsername()))) {
            final Set<Role> roles = new HashSet<>();
            roles.add(personService.getRole((long) 1));
            person.setRoles(roles);

            if (adminRole.equals("1")) {
                person.getRoles().add(personService.getRole((long) 1));
                person.getRoles().add(personService.getRole((long) 2));
            }else {
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

    @GetMapping(value = "/admin")
    public ModelAndView showAllUsers() {
        final ModelAndView listOfPersons = new ModelAndView("persons");
        List<Person> person = personService.allUsers();
        listOfPersons.addObject("persons", person);
        return listOfPersons;
    }

    @GetMapping(value = "/admin/{id}")
    public ModelAndView editUser(@PathVariable("id") long id) {
        final ModelAndView editModel = new ModelAndView("edit");
        Person person = personService.findPerson(id);

        Set<String> set = new HashSet<>();
        for (Role u : person.getRoles()) {
            set.add(u.getRole());
        }
        boolean b = set.contains("ROLE_ADMIN");
        editModel.addObject("person", person);
        editModel.addObject("adminRole", b);
        return editModel;
    }

    @PostMapping(value = "/admin/{id}")
    public ModelAndView deleteUser(@PathVariable("id") long id) {
        personService.delete(id);
        return new ModelAndView("redirect:");
    }
}
