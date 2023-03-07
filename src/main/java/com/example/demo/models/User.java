package com.example.demo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @NotNull(message = "username must not be null")
    @Size(min = 2, max = 100, message = "username length must be between 2 and 100 chars")
    private String username;
    @Size(min = 3, max = 100, message = "password length must be between 2 and 100 chars")
    @Column(name = "password")
    @NotNull(message = "password must not be null")
//    @Pattern(regexp="/(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])", message = "the password must contain at least one digit, uppercase and lowercase letters")
    private String password;
    @Column(name = "dateOfBirth")
    @NotNull
    @Min(value = 1900, message = "date must be granter than 1900")
    @Max(value = 2023, message = "date must be less than 2023")
    private int dateOfBirth;
}
