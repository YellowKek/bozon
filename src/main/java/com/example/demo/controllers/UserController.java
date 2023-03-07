package com.example.demo.controllers;

import com.example.demo.models.User;
import com.example.demo.services.UserService;
import com.example.demo.util.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("user")
public class UserController {
    private final UserService userService;
    private final UserValidator userValidator;

    @Autowired
    public UserController(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }

    @GetMapping("{id}/profile")
    public String profile(@AuthenticationPrincipal org.springframework.security.core.userdetails.User user, Model model) {
        Optional<User> a = userService.findByUsername(user.getUsername());
        if (a.isPresent()) {
            User myUser = a.get();
            model.addAttribute("user", myUser);
            return "user/profile";
        }
        return "redirect:auth/login";
    }

    @GetMapping("change_username")
    public String change_username(@ModelAttribute("user") User user) {
        return "user/change_username";
    }

    @PostMapping("change_username")
    public String change_usernamePost(@ModelAttribute("user") User user) {
//        System.out.println(user.getId());
        return "user/profile";
    }
}
