package com.siemens.vehicleapi.configurations;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@EnableConfigurationProperties(VaultConfiguration.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DatabaseConfiguration {
    @Value("${url}")
    private String postgresUrl;
    @Value("${driverClassName}")
    private String postgresDriverName;
    private VaultConfiguration vaultConfiguration;
    private DataSourceBuilder dataSourceBuilder;

    public DatabaseConfiguration(VaultConfiguration vaultConfiguration) {
        this.vaultConfiguration = vaultConfiguration;
    }

    @Bean
    public DataSource getDataSource() {
        dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url(postgresUrl);
        dataSourceBuilder.username(vaultConfiguration.getPostgresusername());
        dataSourceBuilder.password(vaultConfiguration.getPostgrespassword());
        dataSourceBuilder.driverClassName(postgresDriverName);
        return dataSourceBuilder.build();
    }

}
