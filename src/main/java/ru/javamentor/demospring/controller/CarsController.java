package ru.javamentor.demospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.javamentor.demospring.dao.CarDao;


@RestController
@RequestMapping(value = "/cars")
public class CarsController {
    CarDao dao;

    public CarsController(CarDao dao) {
        this.dao = dao;
    }

    @GetMapping
    public String show(Model model){
        model.addAttribute("counted", dao.getCarList(10));
        return "cars";
    }

    @PostMapping("/{count}")
    public String count(@PathVariable("count") int count, Model model){
        model.addAttribute("counted", dao.getCarList(count));
        return "cars";
    }
}
