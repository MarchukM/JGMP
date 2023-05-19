package com.security.demo.auth.controller;

import com.security.demo.auth.service.LoginAttemptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @Autowired
    private LoginAttemptService loginAttemptService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("blockedUsers")
    @Secured({"ADMIN"})
    public String getBlockedUsers(Model model) {
        model.addAttribute("emails", loginAttemptService.getBlockedEmails());
        return "blocked_users";
    }
}
