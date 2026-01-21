package com.ambermount.warehouse.catalog_service.domain;

import java.math.BigDecimal;

import org.springframework.stereotype.Indexed;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(
    name = "products",
    indexes =  { @Index(name = "idx_products_sku",  columnList = "sku", unique = true)}
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    //product id 
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    //sku 
    @Column(nullable = false, unique = true, length = 64)
    private String sku;

    //name of the  product 
    @Column (nullable = false,
        length = 150
    )
    private String name;

    //Description of the product
    @Column (nullable = false, length = 1000)
    private String description; 


    //Price of the product 
    @Column (nullable = false, 
             precision = 14, scale = 2    )
    private BigDecimal price;

    //Price of the product 
    @Column (nullable = false    )
    private boolean active = true;

    
}
