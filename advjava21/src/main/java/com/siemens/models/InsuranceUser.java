package com.siemens.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public sealed class InsuranceUser permits ClaimOfficer, ClaimAuthorizer {
    protected String name;
    protected String email;
    protected String phoneNumber;
}
