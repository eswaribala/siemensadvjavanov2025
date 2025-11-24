package com.siemens;

import com.siemens.dao.VehicleDao;
import com.siemens.dao.VehicleImpl;
import lombok.NonNull;

public class VarDemo {
    public static void main(String[] args) {
        var orgName = "Siemens";
        System.out.println(orgName.getClass().getSimpleName());
        var employeeCount = 58000;
        Object obj = employeeCount;
        System.out.println(obj.getClass().getSimpleName());

        VehicleDao vehicleDao=new VehicleImpl();
        vehicleDao.getVehicles().stream()
                .filter((@NonNull  var vehicle) -> vehicle.getFuelType().toString().equals("ELECTRIC"))
                .forEach(System.out::println);

    }
}
