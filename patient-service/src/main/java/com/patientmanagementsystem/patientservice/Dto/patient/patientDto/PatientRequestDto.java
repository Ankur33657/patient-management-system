package com.patientmanagementsystem.patientservice.Dto.patient.patientDto;

import com.patientmanagementsystem.patientservice.Dto.enums.PatientStatus;
import com.patientmanagementsystem.patientservice.Dto.validationGroup.CreateValidationGroup;
import com.patientmanagementsystem.patientservice.modules.Patient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.commonservice.types.BloodGroup;
import org.example.commonservice.types.Gender;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientRequestDto {
    @NotBlank(groups = CreateValidationGroup.class, message = "Name is required")
    @Size(min = 3, max = 100, groups = CreateValidationGroup.class, message = "Name should be in range 3-100 Characters")
    private String name;

    @NotBlank(groups = CreateValidationGroup.class, message = "Email is required")
    @Email(groups = CreateValidationGroup.class, message = "Not Valid Email")
    private String email;

    @NotBlank(groups = CreateValidationGroup.class, message = "Address is required")
    private String address;

    @NotBlank(groups = CreateValidationGroup.class, message = "DateOfBirth is required")
    private String dateOfBirth;

    private PatientStatus patientStatus;
    private Gender gender;
    private BloodGroup bloodGroup;
    private String emergencyContactName;
    private String contactNumber;
    private String emergencyContactNumber;



    public static Patient toPatientModel(PatientRequestDto patient){
       Patient p=new Patient();
       p.setName(patient.getName());
       p.setEmail(patient.getEmail());
       p.setAddress(patient.getAddress());
       p.setDateOfBirth(LocalDate.parse(patient.getDateOfBirth()));
       p.setPatientStatus(patient.getPatientStatus());
       p.setGender(patient.getGender());
       p.setBloodGroup(patient.getBloodGroup());
       p.setEmergencyContactName(patient.getEmergencyContactName());
       p.setContactNumber(patient.getContactNumber());
       p.setEmergencyContactNumber(patient.getEmergencyContactNumber());
       return p;


    }

}
