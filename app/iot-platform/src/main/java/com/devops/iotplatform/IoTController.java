package com.devops.iotplatform;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IoTController {

    @GetMapping("/iot")
    public String home() {
        return "Hello from IoT Platform";
    }

    @GetMapping("/iot/hello")
    public String hello() {
        return "Hello Sravanthi! Spring Boot is Working.";
    }
}