package com.siemens.policyholderapi.services;

import com.siemens.policyholderapi.models.PolicyHolder;

import java.util.List;

public interface PolicyHolderService {
    PolicyHolder addPolicyHolder(PolicyHolder policyHolder);
    List<PolicyHolder> findPolicyHolders();
    PolicyHolder findPolicyHolderByPolicyHolderId(String policyHolderId);
    PolicyHolder updatePolicyHolder(String policyHolderId, long mobileNo);
    boolean deletePolicyHolder(String policyHolderId);
}
