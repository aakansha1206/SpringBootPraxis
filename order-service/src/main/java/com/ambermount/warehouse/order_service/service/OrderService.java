package com.ambermount.warehouse.order_service.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ambermount.warehouse.order_service.api.dto.OrderRequest;
import com.ambermount.warehouse.order_service.api.dto.OrderResponse;
import com.ambermount.warehouse.order_service.domain.Order;
import com.ambermount.warehouse.order_service.domain.OrderStatus;
import com.ambermount.warehouse.order_service.exception.GlobalExceptionHandler;
import com.ambermount.warehouse.order_service.exception.NotFoundException;
import com.ambermount.warehouse.order_service.api.dto.OrderResponse;
import com.ambermount.warehouse.order_service.client.CatalogClient;
import com.ambermount.warehouse.order_service.client.CatalogProductResponse;
import com.ambermount.warehouse.order_service.client.InventoryClient;
import com.ambermount.warehouse.order_service.repository.OrderRepository;

@Service
public class OrderService {

    private final GlobalExceptionHandler globalExceptionHandler;

    private final OrderRepository repo;

    private final CatalogClient catalog;
    
    private final InventoryClient inventory;

    public OrderService( OrderRepository repo, CatalogClient catalog, InventoryClient inventory, GlobalExceptionHandler globalExceptionHandler)
    {
        this.repo      = repo;
        this.catalog   = catalog;
        this.inventory = inventory;
        this.globalExceptionHandler = globalExceptionHandler;
    }

    @Transactional
    public OrderResponse createOrder(OrderRequest req)
    {
        String sku        = req.getSku().trim();
        Integer quantity  = req.getQuantity();
          

        CatalogProductResponse product = catalog.getProduct(sku);
        if (product == null)
        {
            Order order = repo.save(new Order(sku, quantity,  BigDecimal.ZERO, "NA", "NA", OrderStatus.REJECTED));
            return toResponse(order, "Product not found in Catalog");
        }  

        BigDecimal unitPrice = product.getPrice() != null ? product.getPrice() : BigDecimal.ZERO;

        String name = product.getName();

        String description = product.getDescription();
        
        
        Integer availableInventory = inventory.getAvailableInventory(sku);

        if (availableInventory == null)
        {
            Order order = repo.save(new Order(sku, quantity, unitPrice, name, description, OrderStatus.REJECTED));
            return toResponse(order, "SKU not found in Inventory");
        }

        if (availableInventory < quantity)
        {
            Order order = repo.save(new Order(sku, quantity, unitPrice, name, description, OrderStatus.REJECTED));
            return toResponse(order, "Insufficient Stock Available");
        }  

        Order order = repo.save(new Order(sku, quantity, unitPrice, name, description, OrderStatus.CONFIRMED));
        return toResponse(order, "Your Order is Confirmed");
     }

     @Transactional
     public OrderResponse getById(Long id)
     {
         Order o = repo.findById(id).
               orElseThrow(() -> new NotFoundException("Order not found: " + id));
          
        return toResponse(o, null);

     }

     @Transactional(readOnly = true)
     public List<OrderResponse> getListAllOrders()
     {
         String message = "Orders Fetched";
         return repo.findAll()
                    .stream()
                    .map(o -> toResponse(o, message)).toList();
     }

        private OrderResponse toResponse(Order o, String message)
        {
            return new OrderResponse(
                o.getId(),
                o.getSku(),
                o.getQuantity(),
                o.getUnitPrice(),
                o.getTotalPrice(),
                o.getName(),
                o.getDescription(),
                o.getStatus(),
                o.getCreatedAt(),
                message
            );
        }

    
}
