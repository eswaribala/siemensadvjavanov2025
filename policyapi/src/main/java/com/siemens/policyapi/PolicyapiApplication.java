package com.siemens.policyapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PolicyapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(PolicyapiApplication.class, args);
    }

}
