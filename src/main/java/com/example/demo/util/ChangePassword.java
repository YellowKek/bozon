package com.example.demo.util;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChangePassword {
    @Size(min = 3, max = 100, message = "password length must be between 2 and 100 chars")
    @NotNull(message = "password must not be null")
    private String newPassword;
    @Size(min = 3, max = 100, message = "password length must be between 2 and 100 chars")
    @NotNull(message = "password must not be null")
    private String repeatedPassword;
}
