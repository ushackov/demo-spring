package ru.javamentor.demospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.javamentor.demospring.dao.CarDao;


@RestController
@RequestMapping(value = "/cars")
public class CarsController {
    CarDao dao;

    public CarsController(CarDao dao) {
        this.dao = dao;
    }

    @GetMapping("/")
    public ModelAndView show(){
        ModelAndView model = new ModelAndView("cars");
        model.addObject("counted", dao.getCarList(10));
        return model;
    }

    @PostMapping
    public ModelAndView count(@RequestParam("count") int count){
        ModelAndView model = new ModelAndView("cars");
        model.addObject("counted", dao.getCarList(count));
        return model;
    }
}
