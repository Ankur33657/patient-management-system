package com.patientmanagementsystem.patientservice.controller;


import com.patientmanagementsystem.patientservice.Dto.patient.AdmitDto.PatientAdmitRequestDto;
import com.patientmanagementsystem.patientservice.Dto.patient.AdmitDto.PatientAdmitResponseDto;
import com.patientmanagementsystem.patientservice.Dto.validationGroup.CreateValidationGroup;
import com.patientmanagementsystem.patientservice.modules.PatientAdmitSummary;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients/admit")

@RequiredArgsConstructor
public class PatientAdmitSummaryController {

//    private PatientAdmitSummary patientAdmitSummary;

//    @GetMapping
//    public ResponseEntity<List<PatientAdmitResponseDto>> getAllPatientAdmitSummary(){
//        return ResponseEntity.status(HttpStatus.OK).body(patientAdmitSummary.getAllPatientAdmitSummary());
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<List<PatientAdmitResponseDto>> getPatientAdmitSummaryById(@PathVariable Long id){
//        return ResponseEntity.status(HttpStatus.OK).body(patientAdmitSummary.getAdmitById(id));
//    }
//
//    @PostMapping()
//    public ResponseEntity<String> admitPatient(@Validated(CreateValidationGroup.class) @RequestBody PatientAdmitRequestDto patientAdmitRequestDto){
//        return ResponseEntity.status(HttpStatus.CREATED).body(patientAdmitSummary.admitPatient(patientAdmitRequestDto));
//    }
//
//    @GetMapping("/admit")
//    public  ResponseEntity<List<PatientAdmitResponseDto>> getAdmitPatient(){
//        return ResponseEntity.status(HttpStatus.OK).body(patientAdmitSummary.getAdmitPatient());
//    }
//
//    @PostMapping("/dischage/{id}")
//    public ResponseEntity<String> dischagePatient(@PathVariable Long id){
//        return ResponseEntity.status(HttpStatus.OK).body(patientAdmitSummary.dischargePatient(id));
//    }
//
//   @PutMapping()
//    public ResponseEntity<PatientAdmitResponseDto> updatePatientAdmit(@Valid @RequestBody PatientAdmitRequestDto patientAdmitRequestDto){
//        return ResponseEntity.status(HttpStatus.OK).body(patientAdmitSummary.updatePatient(patientAdmitRequestDto));
//   }
//
//   @GetMapping("/stats")
//    public ResponseEntity<String> getPatientAdmitStats() {
//        return  ResponseEntity.status(HttpStatus.OK).body(patientAdmitSummary.getPatientAdmitStats());
//   }
//


}
