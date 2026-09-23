package com.patientmanagementsystem.authservice.controller;

import com.patientmanagementsystem.authservice.Dto.User.UserRoleRequestDto;
import com.patientmanagementsystem.authservice.Dto.auth.UserCreateRequestDto;
import com.patientmanagementsystem.authservice.Dto.User.UserResponseDto;
import com.patientmanagementsystem.authservice.services.User.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> GetAllUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponseDto> GetCurrentUser(@RequestHeader("X-User-ID") String currentUserId) {
    Long id=Long.valueOf(currentUserId);
    return ResponseEntity.status(HttpStatus.OK).body(userService.getUserById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDto> GetUserById(@PathVariable Long userId){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserById(userId));
    }


    @PutMapping("/{userId}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long userId,@RequestBody  UserResponseDto userDto){
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(userId,userDto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/role")
    public ResponseEntity<String> getUserRole(
            @RequestHeader("X-User-Role") String role,
            @RequestHeader("X-User-Email") String email) {

        log.info("Email is {} and role is {}", email, role);
        log.info("this is testing log");

        return ResponseEntity.status(HttpStatus.OK).body(role);
    }




}
