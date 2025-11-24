package com.siemens.dtos;

import java.time.LocalDate;

public record VehicleInfo(String registrationNo, LocalDate dateOfRegistration) {
}
