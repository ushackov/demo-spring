package ru.javamentor.demospring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javamentor.demospring.model.Person;

public interface PersonRepository extends JpaRepository<Person,Long> {

}