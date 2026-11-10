package com.patientmanagementsystem.authservice.Dto.User;

import com.patientmanagementsystem.authservice.Dto.types.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class updateRoleRequestDto {
    private Role role;
}
