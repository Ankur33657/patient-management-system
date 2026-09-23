package com.patientmanagementsystem.authservice.Dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class loginRequestDto {

    @NotBlank(message = "Email is required")
    @Email(message = "Email mush be valid ")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;
}
