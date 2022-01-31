package ru.javamentor.demospring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.javamentor.demospring.dao.PeopleRepository;
import ru.javamentor.demospring.model.Person;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DemoSpringApplication {

    public static void main(String[] args) {

        SpringApplication.run(DemoSpringApplication.class, args);

    }

    @Bean
    CommandLineRunner runner(PeopleRepository repository) {
        List<Person> people = new ArrayList<>();
        people.add(new Person("Andrey", "Ushakov", (byte) 33));
        people.add(new Person("Anatoly", "Marandyuk", (byte) 24));
        people.add(new Person("Karina", "Ushakova", (byte) 30));
        return __ -> repository.saveAll(people);
    }
}
