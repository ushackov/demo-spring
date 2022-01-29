package ru.javamentor.demospring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.javamentor.demospring.dao.PeopleRepository;
import ru.javamentor.demospring.model.User;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DemoSpringApplication {

    public static void main(String[] args) {

        SpringApplication.run(DemoSpringApplication.class, args);

    }

    @Bean
    CommandLineRunner runner(PeopleRepository repository) {
        List<User> users = new ArrayList<>();
        users.add(new User("Andrey", "Ushakov", (byte) 33));
        users.add(new User("Anatoly", "Marandyuk", (byte) 24));
        users.add(new User("Karina", "Ushakova", (byte) 30));
        return __ -> repository.saveAll(users);
    }
}
