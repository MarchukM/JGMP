package com.microservices.business.two.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/services")
public class ModuleTwoRestController {

    @GetMapping("/two")
    public String greetings() {
        return "Hello! This is web service two!";
    }
}
