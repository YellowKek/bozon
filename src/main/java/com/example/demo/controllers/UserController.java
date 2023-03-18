package com.example.demo.controllers;

import com.example.demo.models.Product;
import com.example.demo.models.User;
import com.example.demo.services.CartService;
import com.example.demo.services.UserService;
import com.example.demo.util.ChangePassword;
import com.example.demo.util.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("user")
public class UserController {
    private final UserService userService;
    private final UserValidator userValidator;
    private final CartService cartService;

    @Autowired
    public UserController(UserService userService, UserValidator userValidator, CartService cartService) {
        this.userService = userService;
        this.userValidator = userValidator;
        this.cartService = cartService;
    }

    @GetMapping("profile")
    public String profile(@AuthenticationPrincipal org.springframework.security.core.userdetails.User user, Model model) {
        Optional<User> a = userService.findByUsername(user.getUsername());
        if (a.isPresent()) {
            User myUser = a.get();
            model.addAttribute("profile_user", myUser);
            return "user/profile";
        }
        return "redirect:auth/login";
    }

    @GetMapping("change_password")
    public String change_password(@AuthenticationPrincipal org.springframework.security.core.userdetails.User user, Model model) {
        Optional<User> a = userService.findByUsername(user.getUsername());
        if (a.isPresent()) {
            model.addAttribute("pass", new ChangePassword());
            return "/user/change_password";
        }
        return "/auth/login";
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
                return "redirect:/user/profile";
            } else {
                bindingResult.rejectValue("newPassword", "new password", "passwords don't match");
            }
        }
        return "/user/change_password";
    }

    @GetMapping("/cart")
    public String cart(@AuthenticationPrincipal org.springframework.security.core.userdetails.User authUser,
                       Model model) {
        User user = userService.findByUsername(authUser.getUsername()).get();
        model.addAttribute("cart", cartService.findByUser(user));
        return "/user/cart";
    }
}
