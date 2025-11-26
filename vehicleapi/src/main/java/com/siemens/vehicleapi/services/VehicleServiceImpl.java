package com.siemens.vehicleapi.services;

import com.siemens.vehicleapi.exceptions.VehicleNotFoundException;
import com.siemens.vehicleapi.models.Vehicle;
import com.siemens.vehicleapi.repositories.VehicleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    private VehicleRepo vehicleRepo;
    @Override
    public Vehicle addVehicle(Vehicle vehicle) {
        if((vehicle!=null) && (vehicle.getRegistrationNo()!=null)){
            return vehicleRepo.save(vehicle);
        }else {
            return null;
        }
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        return vehicleRepo.findAll();
    }

    @Override
    public Vehicle getVehicleById(String vehicleId) {
        return vehicleRepo.findById(vehicleId).orElseThrow(()->
                new VehicleNotFoundException(vehicleId+"Not Found"));
    }

    @Override
    public Vehicle updateVehicle(String registrationNo, String color) {
        Vehicle vehicle = getVehicleById(registrationNo);
        if(vehicle!=null){
            vehicle.setColor(color);
            return vehicleRepo.save(vehicle);
        }else  {
            return null;
        }
    }

    @Override
    public boolean deleteVehicle(String vehicleId) {
        boolean result = false;
        Vehicle vehicle = getVehicleById(vehicleId);
        if(vehicle!=null) {
            vehicleRepo.delete(vehicle);
            result = true;
        }
        return result;
    }
}
