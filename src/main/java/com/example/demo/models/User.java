package com.example.demo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "username")
    @NotEmpty(message = "username must not be empty")
    @Size(min = 2, max = 30, message = "username length must be between 2 and 30 chars")
    private String username;
    @Size(min = 3, max = 100, message = "password length must be between 5 and 100 chars")
    @Column(name = "password")
    @NotEmpty(message = "password must not be empty")
    private String password;
    @Column(name = "date_of_birth")
    @Min(value = 1900, message = "date must be granter than 1900")
    @Max(value = 2023, message = "date must be less than 2023")
    private int dateOfBirth;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}
