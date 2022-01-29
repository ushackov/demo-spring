package ru.javamentor.demospring.service;

import org.springframework.stereotype.*;
import ru.javamentor.demospring.dao.PeopleRepository;
import ru.javamentor.demospring.model.User;

import java.util.List;

@Service
public class WebService{
    private final PeopleRepository dao;
    public WebService(PeopleRepository dao){
        this.dao = dao;
    }

    public List<User> getAll() {
        return dao.findAll();
    }

    public User get(long id) {
        return dao.getById(id);
    }

    public void save(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        dao.save(user);
    }

    public void delete(long id) {
        dao.deleteById(id);
    }
}
