package com.siemens.policyholderapi.services;

import com.siemens.policyholderapi.models.Address;

import java.util.List;

public interface AddressService {

    Address addAddress(String policyNo,Address address);
    List<Address> getAddresses();
}
