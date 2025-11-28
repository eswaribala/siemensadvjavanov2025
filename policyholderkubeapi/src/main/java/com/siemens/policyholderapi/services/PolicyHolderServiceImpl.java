package com.siemens.policyholderapi.services;

import com.siemens.policyholderapi.exceptions.PolicyHolderNotFoundException;
import com.siemens.policyholderapi.models.PolicyHolder;
import com.siemens.policyholderapi.repositories.PolicyHolderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PolicyHolderServiceImpl implements PolicyHolderService {
    @Autowired
    private PolicyHolderRepo policyHolderRepo;

    @Override
    public PolicyHolder addPolicyHolder(PolicyHolder policyHolder) {

        return policyHolderRepo.save(policyHolder);
    }

    @Override
    public List<PolicyHolder> findPolicyHolders() {
        return policyHolderRepo.findAll();
    }

    @Override
    public PolicyHolder findPolicyHolderByPolicyHolderId(String policyHolderId) {
        return policyHolderRepo.findById(policyHolderId).orElseThrow(()->new PolicyHolderNotFoundException("PolicyHolder not found"));
    }

    @Override
    public PolicyHolder updatePolicyHolder(String policyHolderId, long mobileNo) {
        PolicyHolder policyHolder = findPolicyHolderByPolicyHolderId(policyHolderId);
        if(policyHolder != null){
            policyHolder.setPhoneNumber(mobileNo);
            return policyHolderRepo.save(policyHolder);
        }
        return null;
    }

    @Override
    public boolean deletePolicyHolder(String policyHolderId) {
        boolean deleted = false;
        PolicyHolder policyHolder = findPolicyHolderByPolicyHolderId(policyHolderId);
        if(policyHolder != null) {
            policyHolderRepo.delete(policyHolder);
            deleted = true;
        }
        return deleted;
    }
}
