package com.ambermount.warehouse.inventory_service.api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class InventoryServiceResponse {


    private String sku;

    private Integer quantity;


    // Getters and setters 

    public InventoryServiceResponse(String sku, Integer quantity)
    {
        this.sku = sku;
        this.quantity = quantity;
    }

    public String getSku()
    {
        return sku;
    }

    public Integer getQuantity()
    {
          return quantity;
    }

    
}
