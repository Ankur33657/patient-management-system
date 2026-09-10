package com.patientmanagementsystem.authservice.services.Auth;


import com.patientmanagementsystem.authservice.Dto.auth.UserCreateRequestDto;
import com.patientmanagementsystem.authservice.Dto.auth.loginRequestDto;
import com.patientmanagementsystem.authservice.Dto.auth.loginResponseDto;
import com.patientmanagementsystem.authservice.model.User;
import com.patientmanagementsystem.authservice.repository.UserRepository;
import com.patientmanagementsystem.authservice.utils.JwtUtils;
import io.jsonwebtoken.JwtException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public Optional<String> login(loginRequestDto user) {
      Optional<String> token= userRepository.findByEmail(user.getEmail())
              .filter(u->passwordEncoder.matches(user.getPassword(),u.getPassword()))
              .map(u->jwtUtils.generateToken(u.getEmail(),u.getRole()));

     return token;

    }

    @Override
    public String SignUpUser(UserCreateRequestDto user) {
        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setRole("USER");
        newUser.setName(user.getName());

        userRepository.save(newUser);

        return "USER_CREATED_SUCCESSFULLY";


    }

    @Override
    public boolean validateToken(String token) {
        try{
          jwtUtils.TokenValidation(token);
          return true;
        }catch (Exception e){
          return false;
        }
    }
}
