package com.ambermount.warehouse.inventory_service.api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class InventoryPatchRequest {
    
    @NotNull
    @Min(0)
    private Integer quantity;
        public void setQuantity(Integer quantity)
    {
        this.quantity = quantity;
    }

    public Integer getQuantity()
    {
          return quantity;
    }
}
