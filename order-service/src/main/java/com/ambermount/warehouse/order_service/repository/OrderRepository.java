package com.ambermount.warehouse.order_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ambermount.warehouse.order_service.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    
}
