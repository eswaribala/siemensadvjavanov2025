package com.siemens;

import com.siemens.dao.VehicleDao;
import com.siemens.dao.VehicleImpl;
import com.siemens.models.Vehicle;

import java.util.Comparator;
import java.util.List;

public class VehicleLambdaDemo {
    public static void main(String[] args) {
        VehicleDao vehicleDao = new VehicleImpl();
        //Generate vehicles
        List<Vehicle> vehicles = vehicleDao.getVehicles();
        for(Vehicle vehicle : vehicles){
            System.out.println(vehicle);
        }

        //sort the vehicles based on their date of regn in ascending order
        Comparator<Vehicle> vehicleComparator = (v1,v2)-> v1.getDateOfRegistration().compareTo(v2.getDateOfRegistration());
        vehicles.sort(vehicleComparator);
        //print the sorted vehicles
        System.out.println("-----Sorted Vehicles based on Date of Registration in Ascending Order-----");
        for(Vehicle vehicle : vehicles){
            System.out.println(vehicle);
        }

        

    }
}
