package com.patientmanagementsystem.patientservice.service.patient;


import com.patientmanagementsystem.patientservice.Dto.patient.PatientRequestDto;
import com.patientmanagementsystem.patientservice.Dto.patient.PatientResponseDto;
import com.patientmanagementsystem.patientservice.grpc.BillingServiceGrpcClient;
import com.patientmanagementsystem.patientservice.modules.Patient;
import com.patientmanagementsystem.patientservice.repository.PatientRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor

public class PatientServiceImpl implements PatientService {

    private  PatientRepository patientRepository;
    private BillingServiceGrpcClient billingServiceGrpcClient;

    @Override
    public List<PatientResponseDto> getAllPatients() {
        List<Patient> patients=patientRepository.findAll();
        List<PatientResponseDto> patientResponse=new ArrayList<>();
        for(Patient p:patients){
            patientResponse.add(PatientResponseDto.toPatientResponseDto(p));
        }
        return patientResponse;

    }

    @Override
    public PatientResponseDto createNewPatient(PatientRequestDto patient) {
        Patient p=patientRepository.save(PatientRequestDto.toPatientModel(patient));
        billingServiceGrpcClient.createBillingAccount(p.getId().toString(),p.getName(),p.getEmail());

        return PatientResponseDto.toPatientResponseDto(p);
    }

    @Override
    public PatientResponseDto updatePatient(UUID id, PatientRequestDto patient) {
        Patient p=patientRepository.findById(id).orElseThrow();

        p.setName(patient.getName());
        p.setAddress(patient.getAddress());
        p.setDateOfBirth(LocalDate.parse(patient.getDateOfBirth()));
        Patient updatedPatient = patientRepository.save(p);
        return PatientResponseDto.toPatientResponseDto(updatedPatient);
    }

    @Override
    public void deletePatient(UUID id) {
        patientRepository.deleteById(id);

    }

}
