package com.devops.iotplatform;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String home() {
        return "Welcome to DevOps Master Project!";
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello Sravanthi! Spring Boot is Working.";
    }
}