package com.ambermount.warehouse.order_service.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ambermount.warehouse.order_service.api.dto.OrderRequest;
import com.ambermount.warehouse.order_service.api.dto.OrderResponse;
import com.ambermount.warehouse.order_service.service.OrderService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api/v1/orders")
public class OrderController 
{
    private final OrderService service;

    public OrderController(OrderService service)
    {
        this.service  = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponse createOrder(@RequestBody OrderRequest request) {
        //TODO: process POST request
        
        return service.createOrder(request);
    }

    @GetMapping("/{id}")
    public OrderResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    public List<OrderResponse> getallOrders() {
        return service.getListAllOrders();
    }
       
}
