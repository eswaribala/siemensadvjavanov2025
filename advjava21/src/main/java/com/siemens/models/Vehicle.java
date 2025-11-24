package com.siemens.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Vehicle {
    private String registrationNo;
    private String maker;
    private LocalDate dateOfRegistration;
    private String chassisNo;
    private String engineNo;
    private String color;
    private FuelType fuelType;
}
