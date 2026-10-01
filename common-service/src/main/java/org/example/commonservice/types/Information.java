package org.example.commonservice.types;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Information {
    private LocalDateTime DOB;
    private Gender gender;
    private Address address;
    private String phoneNumber;
    private String alternatePhoneNumber;


}
