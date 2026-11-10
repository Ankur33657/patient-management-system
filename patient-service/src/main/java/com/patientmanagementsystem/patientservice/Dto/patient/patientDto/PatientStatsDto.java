package com.patientmanagementsystem.patientservice.Dto.patient.patientDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientStatsDto {
    private String TotalRegistered;
    private String TotalActive;
    private String FlagPatients;
    private String RegisteredPatientInLast24Hours;
    private String RegisteredPatientInLast7Days;
}
