package com.ambermount.warehouse.catalog_service.api.dto;

import java.math.BigDecimal;
import jakarta.validation.constraints.*;

import jakarta.persistence.Column;

public class ProductRequest {

    //name of the  sku
    @NotBlank
    @Size(max = 64)
    private String sku;

    //name of the  product 
    @NotBlank
    @Size(max = 150)
    private String name;

    //Description of the product
    @NotBlank
    @Size(max = 1000)
    private String description; 


    //Price of the product
    @NotNull 
    @DecimalMin(value = "0.0", inclusive = true)
    @Digits(integer = 12, fraction =  2)
    private BigDecimal price;

    //sku getter setters 
    public void setSku(String sku)
    {
        this.sku = sku;
    }
    public String getSku()
    {
        return sku;
    }

    //Name getter setters 
    public void setName(String name)
    {
        this.name = name;
    }
    public String getName()
    {
        return name;
    }

    //Description getter setters 
    public void setDescription(String description)
    {
        this.description = description;
    }
    public String getDescription()
    {
        return description;
    }


    // Price getter setters 
    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }
    public BigDecimal getPrice()
    {
        return price;
    }

    
}
