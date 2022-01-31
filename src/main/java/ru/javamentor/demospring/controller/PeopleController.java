package ru.javamentor.demospring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.javamentor.demospring.model.Person;
import ru.javamentor.demospring.service.PeopleService;

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
public class PeopleController {

    private final PeopleService peopleService;

    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @GetMapping("")
    public String printWelcome(){
        return "people";
    }

    @GetMapping("/save")
    public ModelAndView newUser(@ModelAttribute("person") Person person){
        return new ModelAndView("save");
    }

    @PostMapping(value = "/table")
    public ModelAndView create(@ModelAttribute("person") Person person){
        if(person.getName() != "" && person.getLastName() != "" && person.getAge() != 0){
            peopleService.save(person);
        }
        return new ModelAndView("redirect:table");
    }

    @GetMapping(value = "/table")
    public ModelAndView showAllUsers(){
        final ModelAndView model = new ModelAndView("table");
        List<Person> people = peopleService.getAll();
        model.addObject("people", people);
        return model;
    }

    @GetMapping(value = "/{id}")
    public ModelAndView showUser(@PathVariable("id") long id, ModelMap model){
        final ModelAndView showPerson = new ModelAndView("showPerson");
        showPerson.addObject("person", peopleService.get(id));
        return showPerson;
    }

    @PostMapping(value = "/{id}")
    public ModelAndView deleteUser(@PathVariable("id") long id){
        peopleService.delete(id);
        return new ModelAndView("redirect:table");
    }
}