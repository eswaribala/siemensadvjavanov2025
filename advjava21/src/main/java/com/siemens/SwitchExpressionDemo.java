package com.siemens;

import com.github.javafaker.Faker;
import com.siemens.models.ClaimAuthorizer;
import com.siemens.models.ClaimOfficer;
import com.siemens.models.InsuranceUser;

public class SwitchExpressionDemo {
    public  static void main(String[] args) {
        Faker faker = new Faker();
        //Runtime Polymorphism
        InsuranceUser insuranceUser= ClaimAuthorizer.builder()
                .name(faker.name().name())
                .email(faker.internet().emailAddress())
                .phoneNumber(faker.phoneNumber().cellPhone())
                .authorizationLevel("LEVEL_"+faker.number().numberBetween(1,5))
                .build();
        InsuranceUser insuranceUser2= ClaimOfficer.builder()
                .name(faker.name().name())
                .email(faker.internet().emailAddress())
                .phoneNumber(faker.phoneNumber().cellPhone())
                .department(faker.company().profession())
                .build();

        System.out.println(ClaimAuthorizer(insuranceUser));
        System.out.println(ClaimAuthorizer(insuranceUser2));
    }

    public static String ClaimAuthorizer(InsuranceUser insuranceUser){
        return switch (insuranceUser){
            case ClaimAuthorizer claimAuthorizer-> "Authorization Level: "+claimAuthorizer.getAuthorizationLevel();
            case ClaimOfficer claimOfficer-> "Insurance User Name: "+claimOfficer.getName();
            default -> "Not a Claim Authorizer";
        };

    }
}
