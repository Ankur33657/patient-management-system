package com.patientmanagementsystem.patientservice.Dto.patient;

import com.patientmanagementsystem.patientservice.modules.PatientAdmitSummary;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientVisitWithPatientIdDto {
    private UUID admittedId;
    private String admittedBy;
    private String relationWithPatient;
    private LocalDateTime admittedAt;
    private String reasonOfAdmitted;
    private LocalDateTime dischargedAt;
    private String reasonOfDischarged;

    public static PatientVisitWithPatientIdDto toPatientVisitWithPatientIdDto(PatientAdmitSummary p){
        return new PatientVisitWithPatientIdDto(p.getId(),p.getAdmittedBy(),
                p.getRelationWithPatient(),p.getAdmittedAt(),
                p.getReasonOfAdmitted(),p.getDischargedAt(),p.getReasonOfDischarged());
    }
}
