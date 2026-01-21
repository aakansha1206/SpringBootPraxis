package com.ambermount.warehouse.inventory_service.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;

public class InventoryServiceRequest {
    
    @NotBlank
    @Size(max = 64)
    private String sku;

    @NotNull
    @Min(0)
    private Integer quantity;


    // Getters and setters 

    public void setSku(String sku)
    {
        this.sku = sku;
    }

    public String getSku()
    {
        return sku;
    }

    public void setQuantity(Integer quantity)
    {
        this.quantity = quantity;
    }

    public Integer getQuantity()
    {
          return quantity;
    }

}
