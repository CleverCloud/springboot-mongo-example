package com.cc.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cc.demo.repository.UserMongoRepository;
import com.cc.demo.entity.User;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserMongoRepository repository;

    public UserController(UserMongoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    List<User> getUsers() {
        return repository.findAll();
    }

    @GetMapping("/{userId}")
    User getUser(@PathVariable String userId) {
        return repository.findById(userId).orElse(null);
    }

    @PostMapping
    User add(@RequestBody User input) {
        return repository.save(new User(input.getFirstName(), input.getLastName()));
    }

    @DeleteMapping("/{userId}")
    void delete(@PathVariable String userId) {
        repository.deleteById(userId);
    }
}
