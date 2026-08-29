package com.patientmanagementsystem.patientservice.controller;

import com.patientmanagementsystem.patientservice.Dto.patient.PatientRequestDto;
import com.patientmanagementsystem.patientservice.Dto.patient.PatientResponseDto;
import com.patientmanagementsystem.patientservice.Dto.validationGroup.createValidationGroup;
import com.patientmanagementsystem.patientservice.service.patient.PatientService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/patients")
@AllArgsConstructor
public class PatientController {

    private PatientService patientService;

    @GetMapping
    public ResponseEntity<List<PatientResponseDto>> getAllPatients() {
        return ResponseEntity.status(HttpStatus.OK).body(patientService.getAllPatients());
    }

    @PostMapping
    public ResponseEntity<PatientResponseDto> CreateNewPatient(@Validated(createValidationGroup.class) @RequestBody PatientRequestDto patient) {
        return ResponseEntity.status(HttpStatus.CREATED).body(patientService.createNewPatient(patient));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientResponseDto> updatePatient(@PathVariable UUID id, @Valid @RequestBody PatientRequestDto patientRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(patientService.updatePatient(id,patientRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable UUID id) {
        patientService.deletePatient(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
