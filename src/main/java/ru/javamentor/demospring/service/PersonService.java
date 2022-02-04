package ru.javamentor.demospring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javamentor.demospring.dao.PersonRepository;
import ru.javamentor.demospring.dao.RoleRepository;
import ru.javamentor.demospring.model.Person;
import ru.javamentor.demospring.model.Role;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService{

    private final PersonRepository repository;
    private final RoleRepository roleRepository;

    public PersonService(PersonRepository repository, RoleRepository roleRepository) {
        this.repository = repository;
        this.roleRepository = roleRepository;
    }

    @Transactional
    public List<Person> allUsers() {
        return repository.findAll();
    }

    @Transactional
    public void add(Person person) {
        repository.save(person);
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public void edit(Person person) {
        repository.save(person);
    }

    @Transactional
    public Person findPerson(Long id) {
        final Optional<Person> byId = repository.findById(id);
        return byId.orElse(null);
    }

    public Role getRole(Long id) {
        return roleRepository.findById(id).orElse(null);
    }
}
