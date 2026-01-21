package com.ambermount.warehouse.catalog_service.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class HealthCheckController {
    
    @GetMapping("/health")
    public String  healthCheck()
    {
       return " catalog-service is reachable";
    }
}
