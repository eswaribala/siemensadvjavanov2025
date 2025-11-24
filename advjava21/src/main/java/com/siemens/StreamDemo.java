package com.siemens;

import com.siemens.dao.VehicleDao;
import com.siemens.dao.VehicleImpl;
import com.siemens.models.FuelType;
import com.siemens.models.Vehicle;

import java.util.Comparator;

public class StreamDemo {

    public  static void main(String[] args) {

        VehicleDao vehicleDao=new VehicleImpl();
        //print all vehicles using streams and method reference
        vehicleDao.getVehicles().stream()
                .filter(v->v.getFuelType()== FuelType.DIESEL)
                .sorted(Comparator.comparing(Vehicle::getDateOfRegistration))
                .forEach(System.out::println);
    }
}
