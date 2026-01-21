package com.ambermount.warehouse.order_service.client;

import java.math.BigDecimal;

public class CatalogProductResponse {

    private String sku;
    private String name;
    private String description;
    private BigDecimal price;
    private boolean active;

    public String getSku() 
    {
        return sku;
    }

    public String getName() 
    {
        return name;
    }

    public String getDescription() 
    {
        return description;
    }

    public BigDecimal getPrice()
    {
        return price;
    }

    public boolean isActive()
    {
        return active;
    }
    
}
