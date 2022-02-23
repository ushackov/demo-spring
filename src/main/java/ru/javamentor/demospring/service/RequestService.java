package ru.javamentor.demospring.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javamentor.demospring.dao.AdminRoleRequestRepository;
import ru.javamentor.demospring.model.AdminRoleRequest;
import ru.javamentor.demospring.model.Person;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RequestService {
    AdminRoleRequestRepository requestRepository;
    PersonService personService;
    DefaultEmailService defaultEmailService;

    public RequestService(AdminRoleRequestRepository requestRepository, PersonService personService, DefaultEmailService defaultEmailService) {
        this.requestRepository = requestRepository;
        this.personService = personService;
        this.defaultEmailService = defaultEmailService;
    }

    @Transactional
    public AdminRoleRequest getRequest(long id) {
        return requestRepository.findByPersonId(id);
    }

    @Transactional
    public void save(AdminRoleRequest roleRequest) {
        String text = String.format("User with id = %s wants to be an Admin", roleRequest.getPersonId());
        final List<String> adminsMail = personService.allPersons().stream()
                .filter(p -> p.getRoles().stream()
                        .anyMatch(f -> f.getAuthority().equals("ROLE_ADMIN")))
                .map(Person::getEmail)
                .collect(Collectors.toList());
        adminsMail.forEach(s -> defaultEmailService
                .sendSimpleEmail(s, "New request from User", text));
        requestRepository.save(roleRequest);
    }

    @Transactional
    public List<AdminRoleRequest> findAll() {
        return requestRepository.findAll();
    }
}
