package com.siemens.policyholderapi.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FullNameRequest {
    @NotEmpty(message = "First name must not be empty")
    @NotBlank(message = "First name must not be blank")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "First Name must contain only alphabets")

    private String firstName;

    private String middleName;
    @NotEmpty(message = "Last name must not be empty")
    @NotBlank(message = "Last name must not be blank")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Last Name must contain only alphabets")

    private String lastName;


}
