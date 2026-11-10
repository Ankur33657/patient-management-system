package com.patientmanagementsystem.patientservice.Dto.patient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientVisitSummaryRequestDto {
    private String admittedBy;
    private String relationWithPatient;
    private LocalDateTime admittedAt;
    private String reasonOfAdmitted;
    private LocalDateTime dischargedAt;
    private String reasonOfDischarged;
}
