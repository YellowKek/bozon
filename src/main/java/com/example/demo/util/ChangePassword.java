package com.example.demo.util;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChangePassword {
    @NotEmpty(message = "password must not be empty")
    private String oldPassword;
    @NotEmpty(message = "password must not be empty")
    @Length(min = 3, message = "min length is 3")
    private String newPassword;
    @NotEmpty(message = "password must not be empty")
    @Length(min = 3, message = "min length is 3")
    private String repeatedPassword;
}
