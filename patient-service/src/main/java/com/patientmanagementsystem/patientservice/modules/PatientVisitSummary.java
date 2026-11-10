package com.patientmanagementsystem.patientservice.modules;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.patientmanagementsystem.patientservice.Dto.enums.VisitType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@Table(name = "patient_visit_summaries")
public class PatientVisitSummary {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "patient_id", nullable = false)
    @JsonIgnore
    private Patient patient;

    @NotBlank
    @Column(nullable = false)
    private String reasonOfVisit;

    @NotBlank
    @Column(nullable = false)
    private String symptoms;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VisitType visitType;

    private Long DoctorId;

    private String diagnosis;

    private String notes;

    private LocalDateTime followUpDate;

    private LocalDateTime scheduledAt;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;
}
