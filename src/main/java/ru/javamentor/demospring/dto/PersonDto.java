package ru.javamentor.demospring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.javamentor.demospring.model.Role;

import java.util.Set;

@Data
@AllArgsConstructor
public class PersonDto {

    private long id;
    private String username;
    private String email;
    private String name;
    private String surname;
    private String password;
    private byte age;
    private Set<Role> roles;
    private boolean isRequested;
}
