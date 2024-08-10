package com.example.demo.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class HelloController {
    private static final Logger logInfo = LoggerFactory.getLogger(HelloController.class);
    @GetMapping("/hello")
    public String sayHello() {
        logInfo.info("Hello myfirst api call with dockerize version");
        logInfo.debug("Hello myfirst api call with dockerize version");
        return "Hello, Spring Boot With Docker!";
    }
    @GetMapping("/{empId}")
    public String getEmployee(String empId) {
        logInfo.info("Hello Employee ID was found");
        logInfo.debug("Hello Employee ID was found");
        return "Employee ID Found";
    }
}
