package com.patientmanagementsystem.patientservice.Dto.patient.VisitDto;

import com.patientmanagementsystem.patientservice.Dto.patient.patientDto.PatientResponseDto;
import java.util.UUID;

public class PatientVisitResponseDto {
    private UUID id;
    private PatientResponseDto patient;
    private String reasonOfVisit;
    private String symptoms;
    private String visitType;
    private Long DoctorId;
    private String diagnosis;
    private String notes;
    private String followUpDate;
    private String scheduledAt;
    private String createdAt;
    private String updatedAt;
}
