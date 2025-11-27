package com.siemens.vehicleapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class VehicleapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(VehicleapiApplication.class, args);
    }

}
