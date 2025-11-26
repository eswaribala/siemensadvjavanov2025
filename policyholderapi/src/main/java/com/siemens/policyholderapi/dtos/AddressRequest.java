package com.siemens.policyholderapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class AddressRequest {

    private long addressId;

    private String doorNo;

    private String street;

    private String city;

    private String state;

    private String zipCode;


}
