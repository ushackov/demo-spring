package ru.javamentor.demospring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "request_role")
public class AdminRoleRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @Column(unique = true)
    private long personId;

    public AdminRoleRequest(long personId){
        this.personId = personId;
    }
}
