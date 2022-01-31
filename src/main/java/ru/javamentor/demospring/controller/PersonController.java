package ru.javamentor.demospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.javamentor.demospring.model.Person;
import ru.javamentor.demospring.service.PersonService;

import java.util.List;

//@Controller
//@RequestMapping(value = "/people")
//public class PeopleController {
//    PeopleService service;
//
//    public PeopleController(PeopleService service) {
//        this.service = service;
//    }
//
//    @GetMapping("/")
//    public ModelAndView showAll(){
//        ModelAndView model = new ModelAndView("people");
//        model.addObject("people", service.getAll());
//        return model;
//    }
//
//    @GetMapping("/show")
//    public ModelAndView showUser(@RequestParam("id")long id){
//        final ModelAndView modelAndView = new ModelAndView("people");
//        modelAndView.addObject("person", service.get(id));
//        return modelAndView;
//    }
//
////    @GetMapping("/save")
////    public ModelAndView save(){
////        return new ModelAndView("save");
////    }
//
//    @PostMapping("/save")
//    public ModelAndView edit(@ModelAttribute("form") Person person){
//        final ModelAndView model = new ModelAndView("save");
//        return model;
//    }
//}


@Controller
@RequestMapping("/people")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("")
    public String printWelcome(){
        return "people";
    }

    @GetMapping("/save")
    public ModelAndView newUser(@ModelAttribute("person") Person person){
        return new ModelAndView("save");
    }

    @PostMapping("/new")
    public ModelAndView create(@ModelAttribute("person") Person person){
        if(person.getName() != "" && person.getLastName() != "" && person.getAge() != 0){
            personService.save(person);
        }
        return new ModelAndView("redirect:table");
    }

    @GetMapping(value = "/table")
    public ModelAndView showAllUsers(){
        final ModelAndView model = new ModelAndView("table");
        List<Person> people = personService.getAll();
        model.addObject("people", people);
        return model;
    }

    @GetMapping(value = "/{id}")
    public ModelAndView showUser(@PathVariable("id") long id){
        final ModelAndView showPerson = new ModelAndView("showPerson");
        showPerson.addObject("person", personService.get(id));
        return showPerson;
    }

    @PostMapping(value = "/{id}")
    public ModelAndView deleteUser(@PathVariable("id") long id){
        personService.delete(id);
        return new ModelAndView("redirect:table");
    }
}