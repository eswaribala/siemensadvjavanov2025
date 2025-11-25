package com.siemens.policyholderapi.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "policy_holders")
public class PolicyHolder {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @PolicyId // custom generator
    @Column(name = "policy_no")
    private String policyNo;
    @Embedded
    private FullName fullName;
    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;
    @Column(name = "phone_number")
    private long phoneNumber;
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    @Column(name = "dob")
    private LocalDate dob;
    @Column(name = "from_date")
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    private LocalDate fromDate;
    @Column(name = "to_date")
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    private LocalDate toDate;
    @Enumerated(EnumType.STRING)
    @Column(name="gender")
    private Gender gender;
}
