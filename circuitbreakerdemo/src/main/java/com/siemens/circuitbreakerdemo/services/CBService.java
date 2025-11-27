package com.siemens.circuitbreakerdemo.services;

import org.springframework.http.ResponseEntity;

public interface CBService {
    ResponseEntity<String> getData();
}
