package com.siemens;

import com.github.javafaker.Faker;
import com.siemens.dao.VehicleDao;
import com.siemens.dao.VehicleImpl;
import com.siemens.models.Vehicle;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class BuiltInFunctionalInterface {
    public static void main(String[] args) {
        Faker faker = new Faker();
        //Function
        VehicleDao vehicleDao = new VehicleImpl();
        //Generate vehicles
        List<Vehicle> vehicles = vehicleDao.getVehicles();
        Function<Vehicle,Long> function=(vehicle)->
                ChronoUnit.YEARS.between(vehicle.getDateOfRegistration(), LocalDate.now());


       System.out.println("Age of Vehicle in Years="+ function.apply(vehicles.get(faker.number().numberBetween(0,9))));

       //BiFunction
        BiFunction<Vehicle,Vehicle,Boolean> biFunction=(v1,v2)->
                v1.getDateOfRegistration().isBefore(v2.getDateOfRegistration());
        System.out.println("Is Vehicle1 Registered before Vehicle2?="+ biFunction.apply(vehicles.get(faker.number().numberBetween(0,5)),vehicles.get(faker.number().numberBetween(6,9))));

    }
}
