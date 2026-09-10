package com.patientmanagementsystem.authservice.Dto.auth;


import com.patientmanagementsystem.authservice.model.User;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateRequestDto {
    @Column(nullable = false,unique = true)
    @Email
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;


    public static User toUserModel(UserCreateRequestDto user) {
        User newuser = new User();
        newuser.setEmail(user.getEmail());
        newuser.setName(user.getName());
        newuser.setPassword(user.getPassword());
        newuser.setRole("ROLE_USER");
        return newuser;
    }
}
