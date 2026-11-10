package org.example.commonservice.types;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Information {
    private LocalDateTime DOB;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Embedded
    private Address address;

    private String phoneNumber;
    private String alternatePhoneNumber;


}
