package com.example.demo.services;

import com.example.demo.models.Favourites;
import com.example.demo.models.User;
import com.example.demo.repos.UserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepo userRepo;
    private final CartService cartService;
    private final PasswordEncoder passwordEncoder;
    private final FavouritesService favouritesService;

    public UserService(UserRepo userRepo, CartService cartService, PasswordEncoder passwordEncoder, FavouritesService favouritesService) {
        this.userRepo = userRepo;
        this.cartService = cartService;
        this.passwordEncoder = passwordEncoder;
        this.favouritesService = favouritesService;
    }

    @Transactional
    public void saveNew(User user) {
        userRepo.save(user);
        favouritesService.save(new Favourites(user));
    }

    @Transactional
    public void save(User user) {
        userRepo.save(user);
    }

    public Optional<User> findById(long id) {
        return userRepo.findById(id);
    }

    @Transactional
    public void update(User updUser, long id) {
        updUser.setId(id);
        save(updUser);
    }

    public String encode(String password) {
        return passwordEncoder.encode(password);
    }

    public boolean matches(String password, String basePassword) {
        return passwordEncoder.matches(password, basePassword);
    }

    public Optional<User> findByUsername(String username) {
        return userRepo.findUserByUsername(username);
    }

}
