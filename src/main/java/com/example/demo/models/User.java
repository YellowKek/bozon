package com.example.demo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @NotNull
    @Size(min = 2, max = 100, message = "username length must be between 2 and 100 chars")
    private String userName;
    @Size(min = 2, max = 100, message = "password length must be between 2 and 100 chars")
    @Column(name = "password")
    @NotNull
    private String password;
    @Column(name = "dateOfBirth")
    @NotNull
    @Min(value = 1900, message = "date must be granter than 1900")
    @Max(value = 2023, message = "date must be less than 2023")
    private int dateOfBirth;
}
