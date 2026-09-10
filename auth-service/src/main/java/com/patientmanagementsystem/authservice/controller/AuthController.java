package com.patientmanagementsystem.authservice.controller;


import com.patientmanagementsystem.authservice.Dto.auth.UserCreateRequestDto;
import com.patientmanagementsystem.authservice.Dto.auth.loginRequestDto;
import com.patientmanagementsystem.authservice.Dto.auth.loginResponseDto;
import com.patientmanagementsystem.authservice.services.Auth.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {


    private  final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<loginResponseDto> login(@Valid @RequestBody loginRequestDto user) {
        Optional<String> token= authService.login(user);

        if(token.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        String tokenValue = token.get();
        return ResponseEntity.status(HttpStatus.OK).body(new loginResponseDto(tokenValue));

    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@Valid @RequestBody UserCreateRequestDto user) {
       return ResponseEntity.status(HttpStatus.CREATED).body(authService.SignUpUser(user));
    }

    @GetMapping("/validate")
    public ResponseEntity<Void> validateToken(@RequestHeader("Authorization") String authHeader){
        // Authorization: Bearer <token>
        if(authHeader==null || !authHeader.startsWith("Bearer ")) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return   authService.validateToken(authHeader.substring(7))
                ? ResponseEntity.status(HttpStatus.OK).build()
                : ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
