package com.delivery_api.Projeto.de.Delivery.API.controller;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class HealthController {

    @GetMapping("/health")
    
    public Map<String, String> health(){
        return Map.of(
            "status", "UP",
            "timestamp", LocalDateTime.now().toString(),
            "Service", "Delivery API"
        );
    }
}
