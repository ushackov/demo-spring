package ru.javamentor.demospring.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.javamentor.demospring.dao.PersonRepository;

@Service
public class PersonDetailsService implements UserDetailsService {
    private final PersonRepository repository;

    public PersonDetailsService(PersonRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return repository.findByUsername(s);
    }
}
