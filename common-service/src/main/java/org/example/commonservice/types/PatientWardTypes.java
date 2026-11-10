package org.example.commonservice.types;

import lombok.Getter;

@Getter
public enum PatientWardTypes {

    ICU("Intensive Care Unit - Critical care for patients requiring continuous monitoring and life support."),
    HDU("High Dependency Unit - For patients needing a higher level of monitoring than a general ward."),
    GENERAL("General Ward - Standard inpatient care for patients requiring hospitalization."),
    PRIVATE("Private Ward - Individual patient accommodation with greater privacy."),
    SEMI_PRIVATE("Semi-Private Ward - Shared accommodation for a limited number of patients."),
    PEDIATRIC("Pediatric Ward - Medical care for infants, children, and adolescents."),
    MATERNITY("Maternity Ward - Care for pregnant women and patients recovering after childbirth."),
    ORTHOPEDIC("Orthopedic Ward - Care for bone, joint, fracture, and musculoskeletal conditions."),
    CCU("Cardiac Care Unit - Specialized monitoring and treatment for serious heart conditions."),
    NICU("Neonatal Intensive Care Unit - Specialized intensive care for newborn babies."),
    PICU("Pediatric Intensive Care Unit - Intensive care for critically ill children."),
    ISOLATION("Isolation Ward - Care for patients requiring specific infection-control precautions."),
    ONCOLOGY("Oncology Ward - Care for patients undergoing cancer treatment."),
    NEUROLOGY("Neurology Ward - Care for patients with disorders of the brain, spinal cord, and nervous system."),
    PSYCHIATRIC("Psychiatric Ward - Inpatient mental health assessment and treatment."),
    BURN("Burn Unit - Specialized care for patients with burn injuries."),
    REHABILITATION("Rehabilitation Ward - Care focused on recovery, mobility, and functional independence.");

    private final String description;

    PatientWardTypes(String description) {
        this.description = description;
    }
}