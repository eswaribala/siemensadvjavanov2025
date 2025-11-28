package com.siemens.policyapi.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.codecs.pojo.annotations.BsonId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "vehicles")
public class Vehicle {

    @BsonId
    private String registrationNo;

    private String maker;

    private LocalDate dateOfRegistration;

    private String chassisNo;

    private String engineNo;

    private String color;

    private FuelType fuelType;
}
