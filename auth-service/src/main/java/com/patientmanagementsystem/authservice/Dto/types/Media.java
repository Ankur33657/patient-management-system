package com.patientmanagementsystem.authservice.Dto.types;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Media {
    private String url;
    private String key;
    private String Type;


}
