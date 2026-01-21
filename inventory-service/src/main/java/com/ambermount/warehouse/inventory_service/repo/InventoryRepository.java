package com.ambermount.warehouse.inventory_service.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ambermount.warehouse.inventory_service.domain.InventoryItem;
import java.util.List;
import java.util.Optional;


public interface InventoryRepository extends JpaRepository<InventoryItem, Long> {

    Optional<InventoryItem> findBySku(String sku);
    boolean existsBySku(String Sku);
    InventoryItem deleteBySku(String sku);
}
