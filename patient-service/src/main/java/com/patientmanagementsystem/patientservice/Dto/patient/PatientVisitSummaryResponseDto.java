package com.patientmanagementsystem.patientservice.Dto.patient;

import com.patientmanagementsystem.patientservice.modules.Patient;
import com.patientmanagementsystem.patientservice.modules.PatientAdmitSummary;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientVisitSummaryResponseDto {
    private Long patientId;
    private String patientName;
    private String patientEmail;
    private String patientAddress;
    private String patientGender;
    private String admittedBy;
    private String relationWithPatient;
    private LocalDateTime admittedAt;
    private String reasonOfAdmitted;
    private LocalDateTime dischargedAt;
    private String reasonOfDischarged;

    public static  PatientVisitSummaryResponseDto toPatientVisitSummaryResponseDto(PatientAdmitSummary patient,Patient p) {
        return new PatientVisitSummaryResponseDto(p.getId(),p.getName(),p.getEmail(),
                p.getAddress(),p.getGender().toString(),patient.getAdmittedBy(),patient.getRelationWithPatient(),
                patient.getAdmittedAt(),patient.getReasonOfAdmitted(),patient.getDischargedAt(),
                patient.getReasonOfDischarged());
    }


}
