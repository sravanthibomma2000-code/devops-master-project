package com.devops.userservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/user")
    public String home() {
        return "Hello from User Service";
    }

    @GetMapping("/user/hello")
    public String hello() {
        return "Hello from User Service! Spring Boot is Working.";
    }
}