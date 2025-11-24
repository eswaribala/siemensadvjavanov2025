package com.siemens;

import com.github.javafaker.Faker;
import com.siemens.dao.VehicleDao;
import com.siemens.dao.VehicleImpl;
import com.siemens.models.FuelType;
import com.siemens.models.Vehicle;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

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

        //Supplier
        //constructor reference
        Supplier<Vehicle> supplier=Vehicle::new;
        Vehicle vehicle=supplier.get();
        //set values
        vehicle.setRegistrationNo("TN-01"+faker.number().numberBetween(1000,9999));
        vehicle.setMaker(faker.company().name());
        vehicle.setColor(faker.color().name());
        vehicle.setDateOfRegistration(LocalDate.now().minusDays(faker.number().numberBetween(1,1000)));
        vehicle.setEngineNo(faker.letterify("??????????"));
        vehicle.setChassisNo(faker.letterify("??????????"));
        //static method reference
        Supplier<FuelType> fuelTypeSupplier=VehicleImpl::randomFuelType;
        vehicle.setFuelType(fuelTypeSupplier.get());
        System.out.println("New Vehicle Details="+vehicle);
        //Instance Method reference
        Supplier<String> vehicleInfoSupplier=vehicle::getEngineNo;
        System.out.println("Engine No of the Vehicle="+vehicleInfoSupplier.get());


        //Predicate to check vehicle registration is before current date
        Predicate<Vehicle> predicate=(v)->
                v.getDateOfRegistration().isBefore(LocalDate.now());
        System.out.println("Is Vehicle Registration Date before Current Date?="+predicate.test(vehicles.get(0)));

    }
}
