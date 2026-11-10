package com.patientmanagementsystem.patientservice.Dto.patient.AdmitDto;

import com.patientmanagementsystem.patientservice.Dto.patient.patientDto.PatientResponseDto;
import org.example.commonservice.types.PatientWardTypes;

import java.time.LocalDateTime;
import java.util.UUID;

public class PatientAdmitResponseDto {
    private UUID id;
    private PatientResponseDto patient;
    private String admittedBy;
    private String relationWithPatient;
    private String reasonOfAdmitted;
    private PatientWardTypes InPatientWardType;
    private LocalDateTime dischargedAt;
    private String reasonOfDischarged;
    private LocalDateTime admittedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
