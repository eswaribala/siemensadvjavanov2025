package com.siemens.circuitbreakerdemo.controllers;

import com.siemens.circuitbreakerdemo.services.CBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cbs")
public class CBController {

    @Autowired
    private CBService cbService;

    @GetMapping("/v1.0")
    public ResponseEntity<String> getStatus() {
        return cbService.getData();
    }
}
