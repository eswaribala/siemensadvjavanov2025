package com.siemens;

import com.siemens.dao.VehicleDao;
import com.siemens.dao.VehicleImpl;

public class StreamDemo {

    public  static void main(String[] args) {

        VehicleDao vehicleDao=new VehicleImpl();
        //print all vehicles using streams and method reference
        vehicleDao.getVehicles().stream().forEach(System.out::println);
    }
}
