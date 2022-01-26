package ru.javamentor.demospring.dao;

import org.springframework.stereotype.Component;
import ru.javamentor.demospring.model.Car;

import java.util.ArrayList;
import java.util.List;

@Component
public class CarDao {
    private final List<Car> carList;

    {
        carList = new ArrayList<>();
        carList.add(new Car("BMW", 1000, 1));
        carList.add(new Car("MB", 2000, 2));
        carList.add(new Car("Mini", 3000, 3));
        carList.add(new Car("Jaguar", 4000, 4));
        carList.add(new Car("Zhiga", 5000, 5));
    }

    public List<Car> getCarList(int count) {
        return count >= carList.size() ? carList : carList.subList(0, count);
    }
}
