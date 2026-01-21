package com.ambermount.warehouse.catalog_service.service;
import org.springframework.stereotype.Service;
import com.ambermount.warehouse.catalog_service.api.dto.ProductResponse;
import com.ambermount.warehouse.catalog_service.api.dto.ProductRequest;
import com.ambermount.warehouse.catalog_service.repo.ProductRepository;
import org.springframework.transaction.annotation.Transactional;
import com.ambermount.warehouse.catalog_service.domain.Product;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.*;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {


private final ProductRepository productRepo;

public ProductService(ProductRepository repo)
{
   this.productRepo = repo;

}

@Transactional
public ProductResponse createProduct(ProductRequest req)
{
    Product p = new Product();
    p.setSku(req.getSku().trim());
    p.setName(req.getName().trim());
    p.setDescription(req.getDescription() == null ? null: req.getDescription().trim());
    p.setPrice(req.getPrice());
    p.setActive(true);

    Product saved = productRepo.save(p);
    return toResponse(saved);
    
}

@Transactional(readOnly = true)
public List<ProductResponse> listProducts()
{
     return  productRepo.findAll().stream().map(this::toResponse).toList();
}

@Transactional(readOnly = true)
public ProductResponse getProductBySku(String sku)
{
    Product p = productRepo.findBySku(sku)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found: " + sku));
                return toResponse(p);
}
@Transactional(readOnly = true)
public ProductResponse getProductByName(String name)
{
    Product p = productRepo.findByName(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found: " + name));
                return toResponse(p);
}
@Transactional(readOnly = true)
public List<ProductResponse> getAllActiveProduct()
{       
        boolean isActive = true;
        return productRepo.findByActive(isActive)
                          .stream()
                          .map(this::toResponse)
                          .toList();
    
}

@Transactional
public ProductResponse deleteProductByName(String reqname)
{     
      Product q = productRepo.findByName(reqname)
                  .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found for deletion on name: " + reqname));
      ProductResponse dto = toResponse(q);

      productRepo.delete(q);

      return dto;    
}

@Transactional
public ProductResponse deleteProductBySku(String sku)
{     
      Product q = productRepo.findBySku(sku)
                  .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found for deletion on SKU: " + sku));
      ProductResponse dto = toResponse(q);

      productRepo.delete(q);

      return dto;
          
     
}

@Transactional
public ProductResponse updateProductBySku(String sku, ProductRequest req)
{
        Product p = productRepo.findBySku(sku.trim())
                 .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found for Update: " + sku));

        p.setDescription(req.getDescription().trim());
        p.setName(req.getName());
        p.setPrice(req.getPrice());
       
        Product saved = productRepo.save(p);

        ProductResponse res = toResponse(saved);
        return res;

}


private ProductResponse toResponse(Product p)
{
    return new ProductResponse(
        p.getSku(),
        p.getName(),
        p.getDescription(),
        p.getPrice(),
        p.isActive()
    );
}
    
}
