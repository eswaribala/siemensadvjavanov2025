package com.siemens.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component //replaces <bean> definition in XML
public class User {
    private String userName;
    private String password;
    private String email;
    private long mobileNo;
}
