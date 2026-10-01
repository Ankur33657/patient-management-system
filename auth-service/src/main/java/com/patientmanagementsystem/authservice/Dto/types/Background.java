package com.patientmanagementsystem.authservice.Dto.types;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.commonservice.types.Media;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Background {

    private String highestDegree;
    private String score;
    private Media degree;
    private boolean isExperienced;
    private float yearOfExperience;
    private String roleAndResponsibility;
    private String organizations;

}
