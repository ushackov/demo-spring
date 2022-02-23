package ru.javamentor.demospring.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javamentor.demospring.dao.AdminRoleRequestRepository;
import ru.javamentor.demospring.dao.PersonRepository;
import ru.javamentor.demospring.dao.RoleRepository;
import ru.javamentor.demospring.model.Person;
import ru.javamentor.demospring.model.Role;

import java.util.*;

@Service
public class PersonService {

    private final PersonRepository repository;
    private final RoleRepository roleRepository;
    private final PersonDetailsService personDetails;
    private final AdminRoleRequestRepository requestRepository;

    public PersonService(PersonRepository repository, RoleRepository roleRepository, PersonDetailsService personDetails,
                         AdminRoleRequestRepository requestRepository) {
        this.repository = repository;
        this.roleRepository = roleRepository;
        this.personDetails = personDetails;
        this.requestRepository = requestRepository;
    }

    @Transactional
    public List<Person> allPersons() {
        return repository.findAll();
    }

    @Transactional
    public boolean add(Person person) {
        if (person.getName() != "" && person.getSurname() != "" && person.getAge() != null &&
                person.getPassword() != "" && person.getUsername() != "" &&
                personDetails.loadUserByUsername(person.getUsername()) == null) {

            person.setRoles(new HashSet<>(Collections.singleton(roleRepository.getById((long) 2))));
            repository.save(person);
            return true;
        } else return false;
    }

    @Transactional
    public void delete(long id) {
        repository.deleteById(id);
    }

    @Transactional
    public boolean edit(Person person, String role) {
        if (person.getName() != "" && person.getSurname() != "" && person.getAge() != null &&
                person.getPassword() != "" && person.getUsername() != "" &&
                (personDetails.loadUserByUsername(person.getUsername()) == null ||
                        repository.getById(person.getId()).getUsername().equals(person.getUsername()))) {
            final Set<Role> roles = new HashSet<>();
            roles.add(roleRepository.getById((long) 1));
            person.setRoles(roles);

            if (role.equals("1")) {
                person.getRoles().add(roleRepository.getById((long) 1));
                person.getRoles().add(roleRepository.getById((long) 2));
                requestRepository.deleteByPersonId(person.getId());
            } else {
                person.getRoles().remove(roleRepository.getById((long) 1));
                person.getRoles().add(roleRepository.getById((long) 2));
                requestRepository.deleteByPersonId(person.getId());
            }
            repository.save(person);
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public Person findPerson(Long id) {
        final Optional<Person> byId = repository.findById(id);
        return byId.orElse(null);
    }

    @Transactional
    public Person getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return repository.findByUsername(auth.getName());
    }

    @Transactional
    public Role getRole(Long id) {
        return roleRepository.findById(id).orElse(null);
    }
}
