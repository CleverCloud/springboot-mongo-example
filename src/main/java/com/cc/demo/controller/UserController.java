package com.cc.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cc.demo.repository.UserMongoRepository;
import com.cc.demo.entity.User;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserMongoRepository repository;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    List<User> getUsers() {
        return repository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{userId}")
    @ResponseBody
    User getUser(@PathVariable String userId) {
        return repository.findOne(userId);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    User add(@RequestBody User input) {
        return repository.save(new User(input.firstName, input.lastName));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{userId}")
    @ResponseBody
    void delete(@PathVariable String userId) {
        repository.delete(userId);
    }
}