package com.siemens;

import com.siemens.dao.VehicleDao;
import com.siemens.dao.VehicleImpl;
import com.siemens.dtos.VehicleInfo;
import com.siemens.models.FuelType;
import com.siemens.models.Vehicle;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
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

        //find first
        vehicleDao.getVehicles().stream().filter(v->v.getColor().equals("white"))
                .findFirst()
                .ifPresent(v-> System.out.println("First Red Vehicle: "+v));

        //predefined collectors
        Map<LocalDate,Vehicle> vehicleMap= vehicleDao.getVehicles().stream()
                .collect(Collectors.toMap(Vehicle::getDateOfRegistration,v->v));
        System.out.println("Vehicle Map: ");
        vehicleMap.entrySet().stream()
                .map(entry->entry.getKey()+" => "+entry.getValue())
                .forEach(System.out::println);

        //custom collector to build comma separated registration numbers
        Collector<Vehicle, StringBuilder, String> registratioNoCollector =
                Collector.of(
                        () -> new StringBuilder(),
                        (sb, v) -> {
                            if (sb.length() > 0) {
                                sb.append(", ");
                            }
                            sb.append(v.getRegistrationNo());
                        },
                        (sb1, sb2) -> {
                            if (sb1.length() > 0 && sb2.length() > 0) {
                                sb1.append(", ");
                            }
                            sb1.append(sb2);
                            return sb1;
                        },
                        StringBuilder::toString
                );
        //applying custom collector
        String registrationNumbers = vehicleDao.getVehicles().stream()
                .collect(registratioNoCollector);
        System.out.println("All Registration Numbers: " + registrationNumbers);

        //exception

      Vehicle vehicle=  vehicleDao.getVehicles().stream().filter(v->v.getFuelType()==FuelType.ELECTRIC).findFirst()
                .orElseThrow(()->new RuntimeException("No Electric Vehicle Found"));
      try{
            System.out.println("Electric Vehicle: "+vehicle);
      }catch (RuntimeException e){
            System.out.println(e.getMessage());
      }

        //parse year of registration to integer list with exception handling
    List<Integer> parsedYears= vehicleDao.getVehicles().stream()
                .map(v->{
                    try{
                       return Integer.parseInt(v.getDateOfRegistration().getYear()+"");
                    }
                    catch (NumberFormatException e){
                        return 0;
                    }
                })
            .filter(year->year>0)
            .toList();

        System.out.println("Parsed Years: "+parsedYears);


    }
}
