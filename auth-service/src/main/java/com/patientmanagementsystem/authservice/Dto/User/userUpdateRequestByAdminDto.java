package com.patientmanagementsystem.authservice.Dto.User;

import org.example.commonservice.types.Media;
import com.patientmanagementsystem.authservice.Dto.types.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class userUpdateRequestByAdminDto {
    private String email;
    private String name;
    private Role role;
    private Media media;
    private boolean enabled;
    private boolean accountLocked;
}
