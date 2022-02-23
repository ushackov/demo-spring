package ru.javamentor.demospring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javamentor.demospring.model.AdminRoleRequest;

public interface AdminRoleRequestRepository extends JpaRepository<AdminRoleRequest, Long> {
    void deleteByPersonId(long id);
    AdminRoleRequest findByPersonId(long id);
    boolean existsByPersonId(Long id);
}
