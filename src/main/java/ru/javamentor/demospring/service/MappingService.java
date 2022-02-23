package ru.javamentor.demospring.service;

import org.springframework.stereotype.Service;
import ru.javamentor.demospring.dao.AdminRoleRequestRepository;
import ru.javamentor.demospring.dto.PersonDto;
import ru.javamentor.demospring.model.Person;

@Service
public class MappingService {
    private final AdminRoleRequestRepository requestRepository;

    public MappingService(AdminRoleRequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    public PersonDto mapToPersonDto(Person person){
        return new PersonDto(person.getId(),
                person.getUsername(),
                person.getEmail(),
                person.getName(),
                person.getSurname(),
                person.getPassword(),
                person.getAge(),
                person.getRoles(),
                requestRepository.existsByPersonId(person.getId()));
    }
}
