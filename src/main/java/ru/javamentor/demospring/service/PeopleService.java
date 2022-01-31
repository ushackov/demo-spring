package ru.javamentor.demospring.service;

import org.springframework.stereotype.*;
import ru.javamentor.demospring.dao.PeopleRepository;
import ru.javamentor.demospring.model.Person;

import java.util.List;

@Service
public class PeopleService {
    private final PeopleRepository repository;
    public PeopleService(PeopleRepository repository){
        this.repository = repository;
    }

    public List<Person> getAll() {
        return repository.findAll();
    }

    public Person get(long id) {
        return repository.getById(id);
    }

    public void save(Person person) {
        repository.save(person);
    }

    public void delete(long id) {
        repository.deleteById(id);
    }

    public void edit(Person person){
        repository.delete(person);
        repository.save(person);
    }
}
