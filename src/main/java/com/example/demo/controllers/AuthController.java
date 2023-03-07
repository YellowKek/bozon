package com.example.demo.controllers;

import com.example.demo.models.User;
import com.example.demo.services.UserService;
import com.example.demo.util.UserValidator;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class AuthController {
    private final UserService userService;
    private final UserValidator userValidator;

    public AuthController(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }

    @GetMapping("home")
    public String home() {
        return "home";
    }

    @GetMapping("")
    public String emptyRequest() {
        return "home";
    }

    @GetMapping("hello")
    public String hello() {
        return "hello";
    }
    @GetMapping("login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("register")
    public String reg(@ModelAttribute("user") User user) {
        return "auth/registration";
    }

    @PostMapping("register")
    public String register(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if (!bindingResult.hasErrors()) {
            userService.save(user);
            return "auth/login";
        }
        return "auth/registration";
    }
}