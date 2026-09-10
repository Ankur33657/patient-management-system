package com.patientmanagementsystem.authservice.Dto.User;


import com.patientmanagementsystem.authservice.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private Long id;
    private String email;
    private String name;
    private String role;

    public static UserResponseDto toUserResponse(User user) {
        UserResponseDto u=new UserResponseDto();
        u.setId(user.getId());
        u.setEmail(user.getEmail());
        u.setName(user.getName());
        u.setRole(user.getRole());

        return u;
    }
}
