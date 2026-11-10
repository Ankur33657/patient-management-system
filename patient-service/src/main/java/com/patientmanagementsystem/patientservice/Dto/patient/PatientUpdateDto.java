package com.patientmanagementsystem.patientservice.Dto.patient;

import com.patientmanagementsystem.patientservice.Dto.enums.PatientStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.commonservice.types.BloodGroup;
import org.example.commonservice.types.Gender;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientUpdateDto {
    private PatientStatus patientStatus;
    private Gender gender;
    private BloodGroup bloodGroup;
    private String emergencyContactNumber;
    private String emergencyContactName;




}
