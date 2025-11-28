package com.siemens.policyapi.configurations;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties
public class VaultConfiguration {
    private String url;
    private String database;
}
