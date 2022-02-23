package ru.javamentor.demospring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javamentor.demospring.model.Role;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
