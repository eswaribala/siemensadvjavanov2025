package com.siemens;

import com.siemens.dao.VehicleDao;
import com.siemens.dao.VehicleImpl;
import com.siemens.models.FuelType;

public class StreamDemo {

    public  static void main(String[] args) {

        VehicleDao vehicleDao=new VehicleImpl();
        //print all vehicles using streams and method reference
        vehicleDao.getVehicles().stream()
                .filter(v->v.getFuelType()== FuelType.DIESEL)
                .sorted((v1,v2)->v1.getDateOfRegistration().compareTo(v2.getDateOfRegistration()))
                .forEach(System.out::println);
    }
}
