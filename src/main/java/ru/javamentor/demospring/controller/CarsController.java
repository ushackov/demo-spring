package ru.javamentor.demospring.controller;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.javamentor.demospring.dao.CarDao;


@RestController
@RequestMapping(value = "/cars")
@ConfigurationProperties("car")
public class CarsController {
    CarDao dao;
    private int max;

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public CarsController(CarDao dao) {
        this.dao = dao;
    }

    @GetMapping("/")
    public ModelAndView show() {
        ModelAndView model = new ModelAndView("cars");
        model.addObject("counted", dao.getCarList(10));
        return model;
    }

    @GetMapping
    public ModelAndView count(@RequestParam("count") int count) {
        ModelAndView model = new ModelAndView("cars");
        if (count > max) {
            model.addObject("counted", dao.getAll());
        } else {
            model.addObject("counted", dao.getCarList(count));
        }
        return model;
    }
}
