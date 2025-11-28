package com.siemens.policyholderapi.services;

import com.siemens.policyholderapi.exceptions.PolicyHolderNotFoundException;
import com.siemens.policyholderapi.models.Address;
import com.siemens.policyholderapi.models.PolicyHolder;
import com.siemens.policyholderapi.repositories.AddressRepo;
import com.siemens.policyholderapi.repositories.PolicyHolderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressRepo addressRepo;
    @Autowired
    private PolicyHolderRepo policyHolderRepo;

    @Override
    public Address addAddress(String policyNo, Address address) {
        PolicyHolder policyHolder=policyHolderRepo.findById(policyNo).orElseThrow(()->new PolicyHolderNotFoundException("PolicyHolder not found"));
        if(policyHolder!=null){
            address.setPolicyHolder(policyHolder);
            return addressRepo.save(address);
        }
        return null;
    }

    @Override
    public List<Address> getAddresses() {
        return this.addressRepo.findAll();
    }
}
