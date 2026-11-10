package com.patientmanagementsystem.patientservice.repository;


import com.patientmanagementsystem.patientservice.Dto.patient.patientDto.PatientResponseDto;
import com.patientmanagementsystem.patientservice.modules.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
  Page<Patient> findByPatientStatus(String status, Pageable pageable);
}
