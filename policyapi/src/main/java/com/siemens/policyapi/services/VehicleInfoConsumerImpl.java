package com.siemens.policyapi.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.siemens.policyapi.models.Vehicle;
import com.siemens.policyapi.repositories.VehicleConsumerRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@KafkaListener(topics = "vehicledata",groupId = "saga-vehicle-group")
@Slf4j
public class VehicleInfoConsumerImpl implements VehicleInfoConsumer {
    @Autowired
    private VehicleConsumerRepo vehicleConsumerRepo;
    @Override
    @KafkaHandler(isDefault = true)
    public void consumeData(String json) {

        log.info("Consumed vehicle data: {}", json);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        // Optional: write dates as ISO-8601 (`"2025-11-27"`) instead of timestamps
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        try {
           Vehicle vehicle = objectMapper.readValue(json, Vehicle.class);
              log.info("Deserialized Vehicle object: {}", vehicle);
             vehicleConsumerRepo.save(vehicle);

        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error serializing vehicle", e);
        }


    }
}
