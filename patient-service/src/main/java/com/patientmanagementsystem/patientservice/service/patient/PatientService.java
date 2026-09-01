package com.patientmanagementsystem.patientservice.service.patient;

import com.patientmanagementsystem.patientservice.Dto.patient.PatientRequestDto;
import com.patientmanagementsystem.patientservice.Dto.patient.PatientResponseDto;

import java.util.List;
import java.util.UUID;

public interface PatientService {
    List<PatientResponseDto> getAllPatients();
    PatientResponseDto createNewPatient(PatientRequestDto patient);
    PatientResponseDto updatePatient(UUID id,PatientRequestDto patient);
    void deletePatient(UUID id);
}
