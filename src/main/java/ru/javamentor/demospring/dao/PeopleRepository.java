package ru.javamentor.demospring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javamentor.demospring.model.User;

public interface PeopleRepository extends JpaRepository<User,Long> {

}
