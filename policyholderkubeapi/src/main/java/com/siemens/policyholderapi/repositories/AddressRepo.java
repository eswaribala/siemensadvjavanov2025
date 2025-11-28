package com.siemens.policyholderapi.repositories;

import com.siemens.policyholderapi.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepo extends JpaRepository<Address,Long> {
}
