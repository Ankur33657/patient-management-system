package com.patientmanagementsystem.patientservice.Dto.patient;

import com.patientmanagementsystem.patientservice.Dto.validationGroup.createValidationGroup;
import com.patientmanagementsystem.patientservice.modules.Patient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientRequestDto {
    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 100,message = "Name should be in range 3-100 Characters")
    private String name;

    @NotBlank(groups = createValidationGroup.class, message = "Email is required")
    @Email(groups = createValidationGroup.class, message = "Not Valid Email")
    private String email;

    @NotBlank(message = "Address is required")
    private String address;

    @NotBlank(message = "DateOfBirth is required")
    private String dateOfBirth;


    public static Patient toPatientModel(PatientRequestDto patient){
       Patient p=new Patient();
       p.setName(patient.getName());
       p.setEmail(patient.getEmail());
       p.setAddress(patient.getAddress());
       p.setDateOfBirth(LocalDate.parse(patient.getDateOfBirth()));
       p.setRegisteredDate(LocalDate.now());
       return p;


    }

}
