package com.ambermount.warehouse.inventory_service.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ambermount.warehouse.inventory_service.api.dto.InventoryServiceRequest;
import com.ambermount.warehouse.inventory_service.api.dto.InventoryServiceResponse;
import com.ambermount.warehouse.inventory_service.api.dto.InventoryPatchRequest;
import com.ambermount.warehouse.inventory_service.domain.InventoryItem;
import com.ambermount.warehouse.inventory_service.service.InventoryService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.*;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {

    private final InventoryService service;

    public InventoryController(InventoryService service)
    {
            this.service = service;
    }
  
    @PostMapping
    public ResponseEntity<InventoryServiceResponse> createInventory(@RequestBody InventoryServiceRequest req) {
         
        InventoryServiceResponse res = service.create(req);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @GetMapping
    public List<InventoryServiceResponse> getAllInventory() {
        return service.getInventory();
    }

    @GetMapping("/sku/{sku}")
    public InventoryServiceResponse getInventoryBySku(@PathVariable String sku) {
        return service.getInventoryBySKu(sku);
    }

    @PatchMapping("/{sku}")
    public InventoryServiceResponse  patchQuantity(@PathVariable String sku, @RequestBody InventoryPatchRequest req) {
        return service.patchBySKu(sku, req);
    }

    @DeleteMapping("/{sku}")
    public InventoryServiceResponse  delete(@PathVariable String sku) {
        return service.deleteBySku(sku);
    }


    
    
   
    
}
