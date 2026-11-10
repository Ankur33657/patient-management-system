package com.patientmanagementsystem.patientservice.Dto.patient.patientDto;

import com.patientmanagementsystem.patientservice.modules.Patient;
import com.patientmanagementsystem.patientservice.modules.PatientAdmitSummary;
import com.patientmanagementsystem.patientservice.modules.PatientVisitSummary;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientResponseDto {
   private String id;
   private String patientCode;
   private String name;
   private String email;
   private String address;
   private String dateOfBirth;
   private String patientStatus;
   private String gender;
   private String bloodType;
   private String contactNumber;
   private String emergencyContactNumber;
   private String emergencyContactName;
   private String createDate;
   private String updateDate;
   private List<PatientAdmitSummary> admissionSummaries;
   private List<PatientVisitSummary> visitSummaries;
   public static PatientResponseDto toPatientResponseDto(Patient patient) {
      return new PatientResponseDto(patient.getId().toString(),patient.getPatientCode(),patient.getName(),patient.getEmail(),patient.getAddress(),patient.getDateOfBirth().toString(),
              patient.getPatientStatus().toString(),patient.getGender().toString(),patient.getBloodGroup().toString(),patient.getContactNumber(),
              patient.getEmergencyContactNumber(),patient.getEmergencyContactName(),patient.getCreatedAt().toString(),patient.getUpdatedAt().toString(),patient.getAdmissionSummaries(),patient.getVisitSummaries());
   }
}
