package com.siemens.policyholderapi.controllers;

import com.siemens.policyholderapi.dtos.GenericResponse;
import com.siemens.policyholderapi.dtos.PolicyHolderRequest;
import com.siemens.policyholderapi.models.FullName;
import com.siemens.policyholderapi.models.PolicyHolder;
import com.siemens.policyholderapi.services.PolicyHolderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/policyholders")
public class PolicyHolderController {
    @Autowired
    private PolicyHolderService policyHolderService;

    @PostMapping("/v1.0")
    public ResponseEntity<GenericResponse> addPolicyHolder(@Valid @RequestBody PolicyHolderRequest policyHolderRequest) {

        PolicyHolder policyHolder = PolicyHolder
                .builder()
                
                .dob(policyHolderRequest.getDob())
                .email(policyHolderRequest.getEmail())
                .fullName(FullName.builder()
                        .firstName(policyHolderRequest.getFullNameRequest().getFirstName())
                        .lastName(policyHolderRequest.getFullNameRequest().getLastName())
                        .middleName(policyHolderRequest.getFullNameRequest().getMiddleName())
                        .build())
                .fromDate(policyHolderRequest.getFromDate())
                .toDate(policyHolderRequest.getToDate())
                .phoneNumber(policyHolderRequest.getPhoneNumber())
                .gender(policyHolderRequest.getGender())
                .email(policyHolderRequest.getEmail())
                .build();

        PolicyHolder savedPolicyHolder = policyHolderService.addPolicyHolder(policyHolder);
        if(savedPolicyHolder != null)
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    new GenericResponse(savedPolicyHolder));
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new GenericResponse("Failed to create policy holder"));

    }

    @GetMapping("/v1.0")
    public List<PolicyHolder> getPolicyHolders() {
        return policyHolderService.findPolicyHolders();
    }

    @GetMapping("/v1.0/{policyHolderId}")
    public ResponseEntity<GenericResponse> getPolicyHolderById(@PathVariable("policyHolderId") String policyHolderId) {
        PolicyHolder policyHolder = policyHolderService.findPolicyHolderByPolicyHolderId(policyHolderId);
        if(policyHolder != null)
            return ResponseEntity.status(HttpStatus.OK).body(
                    new GenericResponse(policyHolder));
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new GenericResponse("Policy holder Not Found"));
    }

    @PatchMapping("/v1.0/{policyHolderId}")
    public ResponseEntity<GenericResponse> updatePolicyHolder(@PathVariable("policyHolderId") String policyHolderId,
                                                              @RequestParam("mobileNo") long mobileNo) {
        PolicyHolder updatedPolicyHolder = policyHolderService.updatePolicyHolder(policyHolderId, mobileNo);
        if (updatedPolicyHolder != null)
            return ResponseEntity.status(HttpStatus.OK).body(
                    new GenericResponse(updatedPolicyHolder));
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new GenericResponse("Failed to update policy holder"));
    }

    @DeleteMapping("/v1.0/{policyHolderId}")
    public ResponseEntity<GenericResponse> deletePolicyHolder(@PathVariable("policyHolderId") String policyHolderId) {
        boolean isDeleted = policyHolderService.deletePolicyHolder(policyHolderId);
        if (isDeleted)
            return ResponseEntity.status(HttpStatus.OK).body(
                    new GenericResponse("Policy holder deleted successfully"));
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new GenericResponse("Failed to delete policy holder"));

    }

}
