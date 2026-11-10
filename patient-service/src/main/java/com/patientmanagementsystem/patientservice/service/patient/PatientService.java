package com.patientmanagementsystem.patientservice.service.patient;

import com.patientmanagementsystem.patientservice.Dto.patient.patientDto.PatientRequestDto;
import com.patientmanagementsystem.patientservice.Dto.patient.patientDto.PatientResponseDto;
import com.patientmanagementsystem.patientservice.Dto.patient.patientDto.PatientStatsDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PatientService {
    Page<PatientResponseDto> getAllPatients(Pageable pageable);
    PatientResponseDto createNewPatient(PatientRequestDto patient);
    PatientResponseDto updatePatient(Long id, PatientRequestDto patient);
    String deletePatient(Long id);
    PatientStatsDto getPatientStats();
    PatientResponseDto getPatientDetails(Long id);
    Page<PatientResponseDto> getPatientByStatus(String status,Pageable pageable);

//    String addPatientVisit(Long id, PatientVisitSummaryRequestDto patient);
//    List<PatientVisitSummaryResponseDto>getAllPatientVisitSummary();
//    PatientResponseDto getAllPatientVisitWithPatient(Long id);
}
