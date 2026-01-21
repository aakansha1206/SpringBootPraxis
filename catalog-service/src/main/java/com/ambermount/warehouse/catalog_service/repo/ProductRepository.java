package com.ambermount.warehouse.catalog_service.repo;
import com.ambermount.warehouse.catalog_service.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;


public interface ProductRepository  extends JpaRepository< Product, Long> {
    Optional<Product> findBySku(String sku);
    Optional<Product> findByName(String name);
    List<Product> findByActive(boolean isActive);
    

    boolean existsBySku(String sku);
}
