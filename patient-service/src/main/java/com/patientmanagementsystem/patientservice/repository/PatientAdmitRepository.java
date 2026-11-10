package com.patientmanagementsystem.patientservice.repository;

import com.patientmanagementsystem.patientservice.modules.PatientAdmitSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface PatientAdmitRepository extends JpaRepository<PatientAdmitSummary, UUID> {
    List<PatientAdmitSummary> findByPatient_PatientCode(String patientCode);

}
