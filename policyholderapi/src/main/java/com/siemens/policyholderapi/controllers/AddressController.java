package com.siemens.policyholderapi.controllers;

import com.siemens.policyholderapi.dtos.AddressRequest;
import com.siemens.policyholderapi.dtos.GenericResponse;
import com.siemens.policyholderapi.models.Address;
import com.siemens.policyholderapi.services.AddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping("/v1.0/{policyNo}")
    public ResponseEntity<GenericResponse> addAddress(@PathVariable("policyNo") String policyNo, @Valid @RequestBody AddressRequest addressRequest) {

        Address address = Address.builder()
                .city(addressRequest.getCity())
                .doorNo(addressRequest.getDoorNo())
                .state(addressRequest.getState())
                .street(addressRequest.getStreet())
                .zipCode(addressRequest.getZipCode())
                .build();
        Address savedAddress = addressService.addAddress(policyNo, address);
        if(savedAddress!=null){
            GenericResponse response = new GenericResponse(savedAddress);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }else {
            GenericResponse response = new GenericResponse("Failed to add address");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping("/v1.0")
    public ResponseEntity<GenericResponse> getAllAddresses() {
       GenericResponse response = new GenericResponse(addressService.getAddresses());
         return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
