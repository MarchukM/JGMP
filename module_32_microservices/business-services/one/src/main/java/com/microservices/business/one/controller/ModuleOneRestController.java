package com.microservices.business.one.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/services")
public class ModuleOneRestController {

    @GetMapping("/one")
    public String greetings() {
        return "Hello! This is web service one!";
    }
}
