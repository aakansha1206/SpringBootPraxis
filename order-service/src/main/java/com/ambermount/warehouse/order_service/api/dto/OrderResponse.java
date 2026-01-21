package com.ambermount.warehouse.order_service.api.dto;

import java.math.BigDecimal;
import java.time.Instant;

import com.ambermount.warehouse.order_service.domain.OrderStatus;

public class OrderResponse {
   private Long id;
   private String sku;
   private Integer quantity;
   private BigDecimal unitPrice;
   private BigDecimal totalPrice;
   private String name;
   private String description;
   private OrderStatus status;
   private Instant createdAt;
   private String message;
   
   public OrderResponse(Long id, String sku, Integer quantity, BigDecimal unitPrice, BigDecimal totalPrice, String name, String description,OrderStatus status, Instant createdAt, String message)
   {
       this.id         = id;
       this.sku        = sku;
       this.quantity   = quantity;
       this.unitPrice  = unitPrice;
       this.totalPrice = totalPrice;
       this.name       = name;
       this.description = description; 
       this.status     = status;
       this.createdAt  = createdAt;
       this.message    = message;
       
   }

   public Long getId() {return id;}
   public String getSku() {return sku;}
   public Integer getQuantity() { return quantity;}
   public BigDecimal getUnitPrice() { return unitPrice;}
   public BigDecimal getTotalPrice() { return totalPrice;}
   public String getName() {return name;}
   public String getDescription() {return description;}
   public OrderStatus getStatus() { return status;}
   public Instant getCreatedAt()  {return createdAt;}
   public String getMessage() {return message;} 


}
