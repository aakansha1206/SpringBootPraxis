package com.ambermount.warehouse.order_service.domain;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import java.time.Instant;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 64)
    private String sku;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private BigDecimal unitPrice;

    @Column(nullable = false)
    private BigDecimal totalPrice;

    @Column(nullable = false, length = 64)
    private String name;

    @Column(nullable = false, length = 1000)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private OrderStatus status;

    @Column(nullable = false)
    private Instant createdAt = Instant.now();

    // For JPA 
    protected Order()
    {

    }

    public Order(String sku, Integer quantity, BigDecimal unitPrice, String name, String description, OrderStatus status)
    {
            this.sku         = sku;
            this.quantity    = quantity;  
            this.unitPrice   = unitPrice;
            this.totalPrice  = unitPrice.multiply(BigDecimal.valueOf(quantity)); 
            this.name        = name;
            this.description = description;
            this.status      = status; 
    }
    
    public Long getId()
    {
        return id;
    }

    public String getSku()
    {
        return sku;
    }

    public Integer getQuantity()
    {
        return quantity;
    }

    public BigDecimal getUnitPrice()
    {
        return unitPrice;
    }

    public BigDecimal getTotalPrice()
    {
         return totalPrice;
    }

    public String getName()
    {
        return name;
    }

    public String getDescription()
    {
        return description;
    }


    public OrderStatus getStatus()
    {
        return status;
    }

    public Instant getCreatedAt()
    {
        return createdAt;
    }

    public void setStatus(OrderStatus status)
    {
        this.status = status;
    }

}
