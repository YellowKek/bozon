package com.example.demo.controllers;

import com.example.demo.models.User;
import com.example.demo.services.UserService;
import com.example.demo.util.ChangePassword;
import com.example.demo.util.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

    @GetMapping("profile")
    public String profile(@AuthenticationPrincipal org.springframework.security.core.userdetails.User user, Model model) {
        Optional<User> a = userService.findByUsername(user.getUsername());
        if (a.isPresent()) {
            User myUser = a.get();
            model.addAttribute("user", myUser);
            return "user/profile";
        }
        return "redirect:auth/login";
    }

    @GetMapping("change_password")
    public String change_password(@AuthenticationPrincipal org.springframework.security.core.userdetails.User user, Model model) {
        Optional<User> a = userService.findByUsername(user.getUsername());
        if (a.isPresent()) {
            model.addAttribute("pass", new ChangePassword());
            return "user/change_password";
        }
        return "auth/login";
    }

    @PostMapping("change_password")
    public String change_password(@AuthenticationPrincipal org.springframework.security.core.userdetails.User authUser,
                                  @ModelAttribute("pass") ChangePassword user, BindingResult bindingResult, Model model) {
        Optional<User> a = userService.findByUsername(authUser.getUsername());
        if (a.isPresent()) {
            User myUser = a.get();
            if (user.getNewPassword().equals(user.getRepeatedPassword())) {
                myUser.setPassword(userService.encode(user.getNewPassword()));
                userService.update(myUser, a.get().getId());
                model.addAttribute("passwordChanged", "Password changed");
            } else {
                bindingResult.rejectValue("newPassword", "new password", "passwords don't match");
            }
        }
//        if (flag)
//            return "forward:/user/profile";
//        else
//            return "/user/change_password";
        return "/user/change_password";
    }
}
