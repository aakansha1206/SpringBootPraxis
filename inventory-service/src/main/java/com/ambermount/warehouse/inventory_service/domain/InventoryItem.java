package com.ambermount.warehouse.inventory_service.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

@Entity
@Table(name = "inventory_items", indexes = {
    @Index(name = "idx_inventory_sku", columnList = "sku", unique = true)
})
public class InventoryItem {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 64, unique = true)
    private String sku;

    @Column(nullable = false)
    private Integer quantity;

    protected InventoryItem()
    {
        
    }
    public InventoryItem(String sku, Integer quantity)
    {
          this.sku = sku;
          this.quantity = quantity;
    }

    public Long getId()
    {
        return id;
    }

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
