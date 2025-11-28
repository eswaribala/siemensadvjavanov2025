package com.siemens.policyholderapi.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="address_id")
    private long addressId;
    @Column(name="door_no", nullable = false, length = 5)
    private String doorNo;
    @Column(name="street", nullable = false, length = 100)
    private String street;
    @Column(name="city", nullable = false, length = 100)
    private String city;
    @Column(name="state", nullable = false, length = 100)
    private String state;
    @Column(name="zip_code", nullable = false, length = 10)
    private String zipCode;
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="policy_no",foreignKey =@ForeignKey(name="fk_policy_no"))
    private PolicyHolder policyHolder;
}
