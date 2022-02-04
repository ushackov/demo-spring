package ru.javamentor.demospring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.javamentor.demospring.dao.PersonRepository;
import ru.javamentor.demospring.model.Person;
import ru.javamentor.demospring.model.Role;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
public class DemoSpringApplication {

    public static void main(String[] args) {

        SpringApplication.run(DemoSpringApplication.class, args);

    }

    @Bean
    CommandLineRunner runner(PersonRepository repository) {
        List<Person> people = new ArrayList<>();
//        people.add(new Person("a","a",(byte)30, "a", "a",Collections.singleton(new Role("ADMIN"))));
        return __ -> repository.saveAll(people);
    }
}
