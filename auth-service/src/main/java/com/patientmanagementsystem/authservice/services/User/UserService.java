package com.patientmanagementsystem.authservice.services.User;

import com.patientmanagementsystem.authservice.Dto.User.*;
import com.patientmanagementsystem.authservice.Dto.auth.UserCreateRequestDto;
import com.patientmanagementsystem.authservice.Dto.types.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface UserService {
    Page<UserResponseDto> getAllUsers(Pageable pageable);
    UserResponseDto getUserById( Long userId);
    UserResponseDto updateUser(Long userId, userUpdateRequestDto userDto);
    UserResponseDto updateUserByAdmin( Long userId, userUpdateRequestByAdminDto userDto);
    String deleteUserById( Long userId);
    UserResponseDto updateUserRole(Long userId, updateRoleRequestDto role);
    Page<UserResponseDto>SearchUser(String keyword,Pageable pageable);
    String updatePassword(String email,passwordUpdateRequestDto password);
    Page<UserResponseDto> getAllInactiveOrLockedUser(Pageable pageable);
    UserResponseDto changeInactiveStatus(Long userId);
}
