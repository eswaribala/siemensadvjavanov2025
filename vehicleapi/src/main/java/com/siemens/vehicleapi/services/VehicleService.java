package com.siemens.vehicleapi.services;

import com.siemens.vehicleapi.models.Vehicle;

import java.util.List;

public interface VehicleService {
    Vehicle addVehicle(Vehicle vehicle);
    List<Vehicle> getAllVehicles();
    Vehicle getVehicleById(String vehicleId);
    Vehicle updateVehicle(String registrationNo, String color);
    boolean deleteVehicle(String vehicleId);
}
