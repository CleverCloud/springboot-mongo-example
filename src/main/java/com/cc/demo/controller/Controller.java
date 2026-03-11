package com.cc.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/")
    public String index() {
        return "Clever-Cloud demo of java with maven, spring-boot and mongodb.";
    }

}
