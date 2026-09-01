package com.patientmanagementsystem.patientservice.Dto.patient;

import com.patientmanagementsystem.patientservice.modules.Patient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientResponseDto {
   private String id;
   private String name;
   private String email;
   private String address;
   private String dateOfBirth;
   private String registeredDate;




   public static PatientResponseDto toPatientResponseDto(Patient patient) {
      return new PatientResponseDto(patient.getId().toString(),patient.getName(),patient.getEmail(),patient.getAddress(),patient.getDateOfBirth().toString(),patient.getRegisteredDate().toString());
   }
}
