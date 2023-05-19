package com.security.demo.web.controller;

import com.security.demo.auth.service.LoginAttemptService;
import com.security.demo.web.service.SomeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class ExampleRestController {

    @Autowired
    private SomeInfoService someInfoService;

    @GetMapping("info")
    @Secured("ADMIN")
    public String getInfo(Model model) {
        model.addAttribute("info", someInfoService.findAll());
        return "info";
    }

    @GetMapping("about")
    @Secured({"ADMIN", "USER"})
    public String getAbout(Model model) {
        return "about";
    }
}
