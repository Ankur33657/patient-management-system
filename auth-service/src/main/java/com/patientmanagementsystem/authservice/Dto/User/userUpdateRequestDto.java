package com.patientmanagementsystem.authservice.Dto.User;

import com.patientmanagementsystem.authservice.Dto.types.Media;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class userUpdateRequestDto {
    private String name;
    private Media media;

}
