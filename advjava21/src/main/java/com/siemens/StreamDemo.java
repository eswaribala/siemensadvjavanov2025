package com.siemens;

import com.siemens.dao.VehicleDao;
import com.siemens.dao.VehicleImpl;
import com.siemens.dtos.VehicleInfo;
import com.siemens.models.FuelType;
import com.siemens.models.Vehicle;

import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamDemo {

    public  static void main(String[] args) {

        VehicleDao vehicleDao=new VehicleImpl();
        //print all vehicles using streams and method reference
        vehicleDao.getVehicles().stream()
                .filter(v->v.getFuelType()== FuelType.DIESEL)
                .sorted(Comparator.comparing(Vehicle::getDateOfRegistration))
                .map(v->new VehicleInfo(v.getRegistrationNo(),v.getDateOfRegistration()))
                .limit(3)
                .forEach(System.out::println);

        //count the number of vehicles based on fuel type
        System.out.println("Group Vehicles by Fuel Type and Count:");
      Map<FuelType,Long> fuelTypeMapCount= vehicleDao.getVehicles().stream()
                .collect(Collectors.groupingBy(Vehicle::getFuelType, Collectors.counting()));
        fuelTypeMapCount.entrySet().stream()
                .map(entry->entry.getKey()+" => "+entry.getValue()).forEach(System.out::println);

        //all match
        boolean allMatch= vehicleDao.getVehicles().stream()
                .allMatch(v->v.getDateOfRegistration().getYear()>=2010);
        System.out.println("All vehicles registered after 2010: "+allMatch);

        //any match
        boolean anyMatch= vehicleDao.getVehicles().stream()
                .anyMatch(v->v.getFuelType()==FuelType.ELECTRIC);
        System.out.println("Any vehicle is Electric: "+anyMatch);
    }
}
