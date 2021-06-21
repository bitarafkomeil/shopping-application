package com.assignment.config;

import com.assignment.model.User;
import com.assignment.model.enumeration.UserRole;
import com.assignment.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

    private final UserRepository repository;

    public Runner(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {
        User user = new User();
        user.setUserName("admin");
        user.setPassword("admin");
        user.setBlock(Boolean.FALSE);
        user.setRole(UserRole.ADMIN);
        repository.save(user);

        user = new User();
        user.setUserName("user");
        user.setPassword("user");
        user.setBlock(Boolean.FALSE);
        user.setRole(UserRole.USER);
        repository.save(user);
    }
}