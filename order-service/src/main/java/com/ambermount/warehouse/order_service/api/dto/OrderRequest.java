package com.ambermount.warehouse.order_service.api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class OrderRequest {
    
    @NotBlank
    @Size(max = 64) 
    private String sku;

    @NotNull
    @Min(1)
    private Integer quantity;

    public String getSku()
    {
         return sku;
    }

    public void setSku(String sku)
    {
        this.sku = sku;
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
