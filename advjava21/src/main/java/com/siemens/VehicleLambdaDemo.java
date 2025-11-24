package com.siemens;

import com.siemens.dao.VehicleDao;
import com.siemens.dao.VehicleImpl;
import com.siemens.models.Vehicle;

public class VehicleLambdaDemo {
    public static void main(String[] args) {
        VehicleDao vehicleDao = new VehicleImpl();
        for(Vehicle vehicle : vehicleDao.getVehicles()){
            System.out.println(vehicle);
        }

    }
}
