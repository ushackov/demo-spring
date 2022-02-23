package ru.javamentor.demospring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javamentor.demospring.model.Person;
import ru.javamentor.demospring.model.Role;

public interface PersonRepository extends JpaRepository<Person,Long> {
Person findByUsername(String name);
Role findRoleById(long id);
}
