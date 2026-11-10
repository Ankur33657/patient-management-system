package com.patientmanagementsystem.patientservice.controller;
import com.patientmanagementsystem.patientservice.Dto.patient.patientDto.PatientRequestDto;
import com.patientmanagementsystem.patientservice.Dto.patient.patientDto.PatientResponseDto;
import com.patientmanagementsystem.patientservice.Dto.patient.patientDto.PatientStatsDto;
import com.patientmanagementsystem.patientservice.Dto.validationGroup.CreateValidationGroup;
import com.patientmanagementsystem.patientservice.service.patient.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/patients")
@Tag(name="Patient" ,description="Api for managing Patients")
@AllArgsConstructor
public class PatientController {

    private PatientService patientService;

    @Operation(summary = "Get All Patients")
    @GetMapping
    public ResponseEntity<Page<PatientResponseDto>> getAllPatients(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size
    , @RequestParam(defaultValue = "id") String sortBy, @RequestParam(defaultValue = "asc")  String sortOrder) {
        Sort sort=Sort.by(Sort.Direction.fromString(sortOrder),sortBy);
        Pageable pageable= PageRequest.of(page,size,sort);
        return ResponseEntity.status(HttpStatus.OK).body(patientService.getAllPatients(pageable));
    }


    @PostMapping
    @Operation(summary = "Create new Patient")
    public ResponseEntity<PatientResponseDto> CreateNewPatient(@Validated(CreateValidationGroup.class) @RequestBody PatientRequestDto patient) {
        return ResponseEntity.status(HttpStatus.CREATED).body(patientService.createNewPatient(patient));
    }



    @PutMapping("/{id}")
    @Operation(summary = "Update a Patient")
    public ResponseEntity<PatientResponseDto> updatePatient(@PathVariable Long id, @RequestBody PatientRequestDto patient) {
        return ResponseEntity.status(HttpStatus.OK).body(patientService.updatePatient(id,patient));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a Patient")
    public ResponseEntity<String> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(patientService.deletePatient(id));
    }

    @GetMapping("/stats")
    public ResponseEntity<PatientStatsDto> getPatientStats(){
        return ResponseEntity.status(HttpStatus.OK).body(patientService.getPatientStats());
    }

    @GetMapping("/{id}")
    public  ResponseEntity<PatientResponseDto> getPatientDetails(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(patientService.getPatientDetails(id));
    }

    @GetMapping("/status")
    public ResponseEntity<Page<PatientResponseDto>> getPatientByStatus(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "id") String sortBy,@RequestParam(defaultValue = "asc") String sortOrder,@RequestParam(defaultValue = "active") String status) {
        Sort sort=Sort.by(Sort.Direction.fromString(sortOrder),sortBy);
        Pageable pageable= PageRequest.of(page,size,sort);
        return ResponseEntity.status(HttpStatus.OK).body(patientService.getPatientByStatus(status,pageable));
    }





}
