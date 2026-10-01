package com.patientmanagementsystem.authservice.Dto.User;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class passwordUpdateRequestDto {

    private String old_password;
    private String new_password;
}
