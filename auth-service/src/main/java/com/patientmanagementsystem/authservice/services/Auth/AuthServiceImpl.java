package com.patientmanagementsystem.authservice.services.Auth;


import com.patientmanagementsystem.authservice.Dto.auth.UserCreateRequestDto;
import com.patientmanagementsystem.authservice.Dto.auth.loginRequestDto;
import com.patientmanagementsystem.authservice.Dto.types.Role;
import com.patientmanagementsystem.authservice.Dto.types.TokenResponse;
import com.patientmanagementsystem.authservice.model.User;
import com.patientmanagementsystem.authservice.repository.UserRepository;
import com.patientmanagementsystem.authservice.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
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
              .map(u->{
                  u.setLastLoginAt(LocalDateTime.now());
                  userRepository.save(u);
                  log.info("Users is {}",u);
                  return jwtUtils.generateToken(u.getEmail(),u.getRole().name(),u.getId());
                      }
              );
     return token;

    }

    @Override
    public String SignUpUser(UserCreateRequestDto user) {
        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setRole(Role.PATIENT);
        newUser.setName(user.getName());

        userRepository.save(newUser);

        return "USER_CREATED_SUCCESSFULLY";


    }

    @Override
    public TokenResponse validateToken(String token) {

        try {
            TokenResponse response = jwtUtils.getEmailAndRoleFromToken(token);

            log.info("TokenResponse before returning: {}", response);

            return response;
        } catch (Exception e) {

            log.error("JWT validation failed", e);

            return null;
        }
    }

}
