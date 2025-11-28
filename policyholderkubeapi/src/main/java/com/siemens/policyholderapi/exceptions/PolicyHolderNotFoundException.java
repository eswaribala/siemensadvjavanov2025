package com.siemens.policyholderapi.exceptions;

public class PolicyHolderNotFoundException extends RuntimeException{
    public PolicyHolderNotFoundException(String message){
        super(message);
    }
}
