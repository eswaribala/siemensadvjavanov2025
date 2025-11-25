package com.siemens.policyholderapi.dtos;

import com.siemens.policyholderapi.models.FullName;
import com.siemens.policyholderapi.models.Gender;
import com.siemens.policyholderapi.models.PolicyId;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PolicyHolderRequest {


    private FullNameRequest fullNameRequest;

    @Email(message = "Email should be valid")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", message = "Email should be valid")
    private String email;
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be a valid 10-digit number")
    private long phoneNumber;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Date of Birth must be in the format YYYY-MM-DD")
    private LocalDate dob;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "From Date must be in the format YYYY-MM-DD")

    private LocalDate fromDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "To Date must be in the format YYYY-MM-DD")

    private LocalDate toDate;

    @Enumerated(EnumType.STRING)
    
    private Gender gender;
}
