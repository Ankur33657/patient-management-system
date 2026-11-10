package com.patientmanagementsystem.patientservice.Dto.patient.AdmitDto;


import com.patientmanagementsystem.patientservice.Dto.validationGroup.CreateValidationGroup;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import org.example.commonservice.types.PatientWardTypes;

public class PatientAdmitRequestDto {
    @Column(nullable = false)
    @NotBlank( message = "PatientId is required")
    private Long patientId;

    @Column(nullable = false)
    @NotBlank(groups = CreateValidationGroup.class, message = "AdmittedBy is required")
    private String admittedBy;

    @Column(nullable = false)
    @NotBlank(groups = CreateValidationGroup.class, message = "relationWithPatient is required")
    private String relationWithPatient;

    @Column(nullable = false)
    @NotBlank(groups = CreateValidationGroup.class, message = "reasonOfAdmitted is required")
    private String reasonOfAdmitted;

    private PatientWardTypes InPatientWardType;
    private String reasonOfDischarged;




}
