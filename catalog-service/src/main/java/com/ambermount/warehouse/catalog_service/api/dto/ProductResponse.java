package com.ambermount.warehouse.catalog_service.api.dto;

import java.math.BigDecimal;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {

     //Information back in the response about sku 
     private String sku;
    
     //Information about product name 
     private String name;

     //Information about the product description 
     private String description; 

     // Information about the product price 
     private BigDecimal price;

     //Information about the product is Active or not 
     private boolean active;


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


    //Description getter setters 
    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }
    public BigDecimal getPrice()
    {
        return price;
    }

   //Description getter setters 
    public void setActive(Boolean active)
    {
        this.active = active;
    }
    public Boolean getActive()
    {
        return active;
    }
    
}
