package com.example.demo.controllers;

import com.example.demo.models.User;
import com.example.demo.services.UserService;
import com.example.demo.util.UserValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Autowired
    public AuthController(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }

    @GetMapping("/")
    public String emptyRequest() {
        return "index";
    }

    @GetMapping("hello")
    public String hello() {
        return "/hello";
    }
    @GetMapping("/login")
    public String login() {
        return "/auth/login";
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            request.getSession().invalidate();
        }
        return "redirect:/";
    }
    @GetMapping("register")
    public String reg(@ModelAttribute("user") User user) {
        return "auth/registration";
    }

    @PostMapping("register")
    public String register(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if (!bindingResult.hasErrors()) {
            user.setPassword(userService.encode(user.getPassword()));
            userService.save(user);
            return "/auth/login";
        }
        return "/auth/registration";
    }

    @GetMapping("forgot_password")
    public String forgot_password(Model model) {
        model.addAttribute("user", new User());
        return "/auth/forgot_password";
    }

    @PostMapping("forgot_password")
    public String forgot_passwordPost(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, Model model) {
        Optional<User> found = userService.findByUsername(user.getUsername());
        if (found.isPresent()) {
            user.setDateOfBirth(found.get().getDateOfBirth());
            String newPass = userValidator.generatePassword();
            model.addAttribute("newPass", newPass);
            user.setPassword(userService.encode(newPass));
            userService.update(user, found.get().getId());
        } else {
            bindingResult.rejectValue("username", "username", "user doesn't exists");
        }
        return "/auth/forgot_password";
    }
}
