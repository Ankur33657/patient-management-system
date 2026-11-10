package com.patientmanagementsystem.patientservice.repository;

import com.patientmanagementsystem.patientservice.modules.PatientVisitSummary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PatientVisitSummaryRepository extends JpaRepository<PatientVisitSummary, UUID> {
}
