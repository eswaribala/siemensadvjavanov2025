package com.siemens.policyholderapi.repositories;

import com.siemens.policyholderapi.models.PolicyHolder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PolicyHolderRepo extends JpaRepository<PolicyHolder,String> {
}
