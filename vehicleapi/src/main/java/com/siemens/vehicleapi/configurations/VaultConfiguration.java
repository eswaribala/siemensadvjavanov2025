package com.siemens.vehicleapi.configurations;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties
public class VaultConfiguration {
    private String postgresusername;
    private String postgrespassword;
}
