package com.siemens.policyapi.configurations;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
@EnableConfigurationProperties(VaultConfiguration.class)
public class DbConfiguration {

    private VaultConfiguration vaultConfiguration;

    public DbConfiguration(VaultConfiguration vaultConfiguration) {
        this.vaultConfiguration = vaultConfiguration;
    }
    @Bean
    public MongoClient mongoClient() {
        // Use vaultConfiguration to get connection details
        return MongoClients.create(vaultConfiguration.getUrl());
    }


    @Bean
    public MongoTemplate mongoTemplate(){
        return new MongoTemplate(mongoClient(),vaultConfiguration.getDatabase());
    }
}
