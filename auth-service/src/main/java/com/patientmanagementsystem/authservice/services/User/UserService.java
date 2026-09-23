package com.patientmanagementsystem.authservice.services.User;

import com.patientmanagementsystem.authservice.Dto.auth.UserCreateRequestDto;
import com.patientmanagementsystem.authservice.Dto.User.UserResponseDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface UserService {
    List<UserResponseDto> getAllUsers();
    UserResponseDto getUserById(@PathVariable Long userId);
    UserResponseDto updateUser(@PathVariable Long userId,UserResponseDto userDto);

}
