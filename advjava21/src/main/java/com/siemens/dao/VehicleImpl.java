package com.siemens.dao;

import com.github.javafaker.Faker;
import com.siemens.models.FuelType;
import com.siemens.models.Vehicle;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class VehicleImpl implements VehicleDao {
    @Override
    public List<Vehicle> getVehicles() {
      List<Vehicle> vehicles = new ArrayList<>();
      Faker faker = new Faker();
      for (int i = 0; i < 10; i++) {
            Vehicle vehicle = Vehicle.builder()
                            .registrationNo("TN-01"+faker.number().numberBetween(1000,9999))
                            .maker(faker.company().name())
                            .color(faker.color().name())
                            .chassisNo(faker.letterify("??????????"))
                            .engineNo(faker.letterify("??????????"))
                            .dateOfRegistration(faker.date().birthday().toInstant()
                                    .atZone(ZoneId.systemDefault()).toLocalDate())
                            .fuelType(randomFuelType())
                            .build();

            vehicles.add(vehicle);
      }
      return vehicles;
    }
    public static FuelType randomFuelType(){
        FuelType[] fuelTypes = FuelType.values();
        return fuelTypes[(int)(Math.random()*fuelTypes.length)];
    }
}
