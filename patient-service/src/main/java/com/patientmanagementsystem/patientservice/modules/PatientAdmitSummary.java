package com.patientmanagementsystem.patientservice.modules;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import org.example.commonservice.types.PatientWardTypes;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "patient_admit_summaries")
public class PatientAdmitSummary {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "patient_id", nullable = false)
    @JsonIgnore
    private Patient patient;

    @NotBlank
    @Column(nullable = false)
    private String admittedBy;

    @NotBlank
    @Column(nullable = false)
    private String relationWithPatient;



    @NotBlank
    @Column(nullable = false)
    private String reasonOfAdmitted;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private PatientWardTypes InPatientWardType = PatientWardTypes.GENERAL;

    private LocalDateTime dischargedAt;

    private String reasonOfDischarged;

    private LocalDateTime admittedAt;
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;
}