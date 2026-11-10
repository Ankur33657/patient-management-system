package com.patientmanagementsystem.patientservice.Dto.patient.VisitDto;

import com.patientmanagementsystem.patientservice.Dto.enums.VisitType;
import com.patientmanagementsystem.patientservice.Dto.validationGroup.CreateValidationGroup;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public class PatientVisitRequestDto {
    @Column(nullable = false)
    private Long patientId;
    @NotBlank(groups = CreateValidationGroup.class,message = "reasonOfVisit is required")
    private String reasonOfVisit;
    @NotBlank(groups = CreateValidationGroup.class,message = "Symptoms is required")
    private String symptoms;
    @NotBlank(groups = CreateValidationGroup.class,message = "visitType is required")
    private String visitType;
    private Long DoctorId;
    private String diagnosis;
    private String notes;
    private LocalDateTime followUpDate;
    private LocalDateTime scheduledAt;

}
