package com.patientmanagementsystem.patientservice.service.patient;


import com.patientmanagementsystem.patientservice.Dto.enums.PatientStatus;
import com.patientmanagementsystem.patientservice.Dto.patient.patientDto.PatientRequestDto;
import com.patientmanagementsystem.patientservice.Dto.patient.patientDto.PatientResponseDto;
import com.patientmanagementsystem.patientservice.Dto.patient.patientDto.PatientStatsDto;
import com.patientmanagementsystem.patientservice.grpc.BillingServiceGrpcClient;
import com.patientmanagementsystem.patientservice.kafka.KafkaProducer;
import com.patientmanagementsystem.patientservice.modules.Patient;
import com.patientmanagementsystem.patientservice.repository.PatientAdmitRepository;
import com.patientmanagementsystem.patientservice.repository.PatientRepository;
import lombok.extern.slf4j.Slf4j;
import org.example.commonservice.exception.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service


public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final PatientAdmitRepository patientAdmitRepository;
    private final BillingServiceGrpcClient billingServiceGrpcClient;
    private final KafkaProducer kafkaProducer;

    public PatientServiceImpl(PatientRepository patientRepository,PatientAdmitRepository patientAdmitRepository, BillingServiceGrpcClient billingServiceGrpcClient, KafkaProducer kafkaProducer) {
        this.patientRepository = patientRepository;
        this.patientAdmitRepository = patientAdmitRepository;
        this.billingServiceGrpcClient = billingServiceGrpcClient;
        this.kafkaProducer = kafkaProducer;
    }


    @Override
    public Page<PatientResponseDto> getAllPatients(Pageable pageable) {
        return patientRepository.findAll(pageable).map(patient->PatientResponseDto.toPatientResponseDto(patient));


    }

    @Override
    public PatientResponseDto createNewPatient(PatientRequestDto patient) {
        Patient p=PatientRequestDto.toPatientModel(patient);


       Patient p1= patientRepository.save(p);
        p1.setPatientCode("PT-" + p.getId());
        patientRepository.save(p1);
//        billingServiceGrpcClient.createBillingAccount(p.getId().toString(),p.getName(),p.getEmail());

//        kafkaProducer.sendPatientCreatedEvent(p);

        return PatientResponseDto.toPatientResponseDto(p);
    }
    @Override
    public PatientResponseDto updatePatient(Long id, PatientRequestDto patient) {
        Patient p=patientRepository.findById(id).orElseThrow(()->new NotFoundException("Patient Not Found"));
        p.setPatientStatus(patient.getPatientStatus());
        p.setGender(patient.getGender());
        p.setBloodGroup(patient.getBloodGroup());
        p.setEmergencyContactNumber(patient.getEmergencyContactNumber());
        p.setEmergencyContactName(patient.getEmergencyContactName());
        Patient updatedPatient = patientRepository.save(p);
        return PatientResponseDto.toPatientResponseDto(updatedPatient);
    }

    @Override
    public String deletePatient(Long id) {
        Patient p=patientRepository.findById(id).orElseThrow(()->new NotFoundException("Patient Not Found"));
        patientRepository.delete(p);
        return "Patient has been deleted Successfully";


    }

    @Override
    public PatientStatsDto getPatientStats() {

        List<Patient> patients = patientRepository.findAll();

        PatientStatsDto patientStats = new PatientStatsDto();

        int totalPatients = patients.size();
        int patientsRegisteredLast24Hours = 0;
        int patientsRegisteredLast7Days = 0;
        int activePatients = 0;
        int flagPatients = 0;

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime last24Hours = now.minusHours(24);
        LocalDateTime last7Days = now.minusDays(7);

        for (Patient p : patients) {
            if (p.getCreatedAt() != null &&
                    !p.getCreatedAt().isBefore(last24Hours) &&
                    !p.getCreatedAt().isAfter(now)) {

                patientsRegisteredLast24Hours++;
            }
            if (p.getCreatedAt() != null &&
                    !p.getCreatedAt().isBefore(last7Days) &&
                    !p.getCreatedAt().isAfter(now)) {

                patientsRegisteredLast7Days++;
            }
            if (p.getPatientStatus() == PatientStatus.ACTIVE) {
                activePatients++;
            }
        }

        patientStats.setTotalRegistered(String.valueOf(totalPatients));
        patientStats.setRegisteredPatientInLast24Hours(
                String.valueOf(patientsRegisteredLast24Hours));
        patientStats.setRegisteredPatientInLast7Days(
                String.valueOf(patientsRegisteredLast7Days));
        patientStats.setTotalActive(String.valueOf(activePatients));
        patientStats.setFlagPatients(String.valueOf(flagPatients));

        return patientStats;
    }

    @Override
    public PatientResponseDto getPatientDetails(Long id) {
       Patient p=patientRepository.findById(id).orElseThrow(()->new NotFoundException("Patient Not Found"));
       return PatientResponseDto.toPatientResponseDto(p);
    }

    @Override
    public Page<PatientResponseDto> getPatientByStatus(String status,Pageable pageable) {

        return patientRepository.findByPatientStatus(status.toUpperCase(),pageable).map(patient -> PatientResponseDto.toPatientResponseDto(patient));
    }

//    @Override
//    public  String addPatientVisit(Long id, PatientVisitSummaryRequestDto patient) {
//        Patient p=patientRepository.findById(id).orElseThrow();
//        PatientAdmitSummary patientAdmitSummary=new PatientAdmitSummary();
//        patientAdmitSummary.setPatient(p);
//        patientAdmitSummary.setAdmittedBy(patient.getAdmittedBy());
//        patientAdmitSummary.setRelationWithPatient(patient.getRelationWithPatient());
//        patientAdmitSummary.setReasonOfAdmitted(patient.getReasonOfAdmitted());
//        patientAdmitRepository.save(patientAdmitSummary);
//        return  "SUCCESSFULLY ADMITTED PATIENT";
//    }
//
//    @Override
//    public List<PatientVisitSummaryResponseDto> getAllPatientVisitSummary() {
////        List<PatientAdmitSummary> p= patientAdmitRepository.findAllWithPatient();
////        List<PatientVisitSummaryResponseDto> patientVisitSummaryResponseDto=new ArrayList<>();
////        for (PatientAdmitSummary patientAdmitSummary : p) {
////            patientVisitSummaryResponseDto.add(PatientVisitSummaryResponseDto.toPatientVisitSummaryResponseDto(patientAdmitSummary,patientAdmitSummary.getPatient()));
////        }
////        return patientVisitSummaryResponseDto;
//        return null;
//    }
//
//    @Override
//    public PatientResponseDto getAllPatientVisitWithPatient(Long id) {
//       Patient p=patientRepository.findById(id).orElseThrow();
//       PatientResponseDto patient=PatientResponseDto.toPatientResponseDto(p);
//       return patient;
//    }
}
