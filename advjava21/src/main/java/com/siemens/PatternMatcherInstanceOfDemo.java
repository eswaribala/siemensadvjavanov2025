package com.siemens;

import com.github.javafaker.Faker;
import com.siemens.models.ClaimAuthorizer;
import com.siemens.models.InsuranceUser;

public class PatternMatcherInstanceOfDemo {
    public static void main(String[] args) {

        Faker faker = new Faker();
        //Runtime Polymorphism
        InsuranceUser insuranceUser= ClaimAuthorizer.builder()
                .name(faker.name().name())
                .email(faker.internet().emailAddress())
                .phoneNumber(faker.phoneNumber().cellPhone())
                .authorizationLevel("LEVEL_"+faker.number().numberBetween(1,5))
                .build();
        //pattern matching for instanceof
        if(insuranceUser instanceof ClaimAuthorizer claimAuthorizer){
            System.out.println("Authorization Level: "+claimAuthorizer.getAuthorizationLevel());
        }else {
            System.out.println("Not a Claim Authorizer");
        }

    }
}
