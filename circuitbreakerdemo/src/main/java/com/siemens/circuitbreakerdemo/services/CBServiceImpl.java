package com.siemens.circuitbreakerdemo.services;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@Slf4j
public class CBServiceImpl implements CBService {

    @Value("${serviceUrl}")
    private String apiUrl;
    @Value("${serviceUrl1}")
    private String apiUrl1;
    @Value("${alternativeServiceUrl}")
    private String alternativeUrl;

    @Autowired
    private RestClient restClient;

    @Override
    @CircuitBreaker(name = "gatewayCircuitBreaker", fallbackMethod = "fallback")
    @Retry(name = "gatewayRetry")
    @RateLimiter(name="gatewayRateLimiter")
    public ResponseEntity<String> getData() {
        log.info("Attempting to fetch data from primary API: {}", apiUrl);
       return  restClient.get().uri(apiUrl).retrieve().toEntity(String.class);
    }
    @CircuitBreaker(name = "gatewayCircuitBreaker", fallbackMethod = "fallback1")
    @Retry(name = "gatewayRetry")
    @RateLimiter(name="gatewayRateLimiter")
    public ResponseEntity<String> fallback(Exception ex) {
        log.warn("Primary API call failed: {}. Redirecting to Policy API: {}", ex.getMessage(), alternativeUrl);
        return restClient.get().uri(apiUrl1).retrieve().toEntity(String.class);

    }

    public ResponseEntity<String> fallback1(Exception ex) {
        log.error("Both primary and alternative API calls failed: {}", ex.getMessage());
        return restClient.get().uri(alternativeUrl).retrieve().toEntity(String.class);
    }
}
