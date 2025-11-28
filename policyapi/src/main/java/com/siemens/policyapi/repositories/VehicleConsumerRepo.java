package com.siemens.policyapi.repositories;

import com.siemens.policyapi.models.Vehicle;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

public interface VehicleConsumerRepo extends MongoRepository<Vehicle, String> {
}
