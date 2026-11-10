package com.patientmanagementsystem.patientservice.controller;

import com.patientmanagementsystem.patientservice.Dto.patient.VisitDto.PatientVisitRequestDto;
import com.patientmanagementsystem.patientservice.Dto.patient.VisitDto.PatientVisitResponseDto;
import com.patientmanagementsystem.patientservice.Dto.patient.patientDto.PatientResponseDto;
import com.patientmanagementsystem.patientservice.Dto.patient.PatientVisitSummaryRequestDto;
import com.patientmanagementsystem.patientservice.Dto.patient.PatientVisitSummaryResponseDto;
import com.patientmanagementsystem.patientservice.Dto.validationGroup.CreateValidationGroup;
import com.patientmanagementsystem.patientservice.modules.PatientVisitSummary;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients/summary")
@RequiredArgsConstructor
public class PatientVisitSummaryController {

//    private PatientVisitSummary patientVisitSummary;

//    @PostMapping
//    public ResponseEntity<PatientVisitResponseDto> addPatientVisit(@Validated(CreateValidationGroup.class) @RequestBody PatientVisitRequestDto patient){
//        return  ResponseEntity.status(HttpStatus.CREATED).body(patientVisitSummary.addPatientVisit(patient));
//    }
//
//    @GetMapping
//    public  ResponseEntity<List<PatientVisitSummaryResponseDto>> getAllPatientVisitSummary(){
//        return ResponseEntity.status(HttpStatus.OK).body(patientVisitSummary.getAllPatientVisitSummary());
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<PatientResponseDto> getAllPatientVisitWithPatient(@PathVariable Long id){
//        return ResponseEntity.status(HttpStatus.OK).body(patientVisitSummary.getAllPatientVisitWithPatient(id));
//    }


}
