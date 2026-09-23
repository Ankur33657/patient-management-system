package com.patientmanagementsystem.authservice.services.Auth;

import com.patientmanagementsystem.authservice.Dto.auth.UserCreateRequestDto;
import com.patientmanagementsystem.authservice.Dto.auth.loginRequestDto;
import com.patientmanagementsystem.authservice.Dto.auth.loginResponseDto;
import com.patientmanagementsystem.authservice.Dto.types.TokenResponse;

import java.util.Optional;

public interface AuthService {
    Optional<String> login(loginRequestDto user);
    String SignUpUser(UserCreateRequestDto user);
    TokenResponse validateToken(String token);

}
