package com.patientmanagementsystem.authservice.Dto.types;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenResponse {
    String email;
    String role;
    Long id;
}
