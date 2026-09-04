package com.patientmanagementsystem.billingservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class hellocontroller {


    @GetMapping("/h")
    public String hello() {
        return "Hello World";
    }
}
