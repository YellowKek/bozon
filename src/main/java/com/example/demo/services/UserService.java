package com.example.demo.services;

import com.example.demo.models.User;
import com.example.demo.repos.UserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void save(User user) {
        user.setPassword(encode(user.getPassword()));
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
