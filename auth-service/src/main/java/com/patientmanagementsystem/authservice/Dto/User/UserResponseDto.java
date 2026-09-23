package com.patientmanagementsystem.authservice.Dto.User;


import com.patientmanagementsystem.authservice.Dto.types.Media;
import com.patientmanagementsystem.authservice.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private Long id;
    private String email;
    private String name;
    private String role;
    private Media media;
    private boolean enabled;
    private boolean accountLocked;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime lastLogin;

    public static UserResponseDto toUserResponse(User user) {
        UserResponseDto u=new UserResponseDto();
        u.setId(user.getId());
        u.setEmail(user.getEmail());
        u.setName(user.getName());
        u.setRole(user.getRole().name());
        u.setMedia(user.getMedia());
        u.setEnabled(user.isEnabled());
        u.setAccountLocked(user.isAccountLocked());
        u.setCreatedAt(user.getCreatedAt());
        u.setUpdatedAt(user.getUpdatedAt());
        u.setLastLogin(user.getLastLoginAt());

        return u;
    }
}
