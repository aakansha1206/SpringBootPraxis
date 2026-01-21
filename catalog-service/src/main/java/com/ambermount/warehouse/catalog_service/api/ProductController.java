package com.ambermount.warehouse.catalog_service.api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.ambermount.warehouse.catalog_service.api.dto.ProductRequest;
import com.ambermount.warehouse.catalog_service.api.dto.ProductResponse;
import com.ambermount.warehouse.catalog_service.service.ProductService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.*;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

   private final ProductService service;

   public ProductController(ProductService service)
   {
       this.service = service; 
   }

   @PostMapping
   @ResponseStatus(HttpStatus.CREATED)
   public ProductResponse create(@Valid @RequestBody ProductRequest request)
   {
         return service.createProduct(request);
   }

   @GetMapping
   public List<ProductResponse> list()
   {
     return service.listProducts();
   }

   @GetMapping("/sku/{sku}")
   public ProductResponse getBySku(@PathVariable String sku)
   {
        return service.getProductBySku(sku);
   }
   
   @GetMapping("name/{name}")
   public ProductResponse getByName(@PathVariable String name)
   {
        return service.getProductByName(name);
   }
   @GetMapping("/activeProducts")
   public List<ProductResponse> getAllActive()
   {
        return service.getAllActiveProduct();
   }
   
   @PutMapping("/{sku}")
   public ProductResponse updateBySku(@PathVariable String sku, @RequestBody ProductRequest request) {
      
       return service.updateProductBySku(sku, request);
   }

   @DeleteMapping("sku/{sku}")
   public ProductResponse deletebySku(@PathVariable String sku)
   {
        return service.deleteProductBySku(sku);
   }

   @DeleteMapping("name/{name}")
   public ProductResponse deletebyName(@PathVariable String name)
   {
        return service.deleteProductByName(name);
   }
   
    
}
