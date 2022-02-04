package ru.javamentor.demospring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javamentor.demospring.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
