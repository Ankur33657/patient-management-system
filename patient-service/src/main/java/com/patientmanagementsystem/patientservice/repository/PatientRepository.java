package com.patientmanagementsystem.patientservice.repository;


import com.patientmanagementsystem.patientservice.modules.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PatientRepository extends JpaRepository<Patient, UUID> {
  boolean existsByEmail(String email);
}
