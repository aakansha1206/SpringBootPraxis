package com.ambermount.warehouse.inventory_service.service;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ambermount.warehouse.inventory_service.api.dto.InventoryServiceRequest;
import com.ambermount.warehouse.inventory_service.api.dto.InventoryServiceResponse;
import com.ambermount.warehouse.inventory_service.api.dto.InventoryPatchRequest;
import com.ambermount.warehouse.inventory_service.domain.InventoryItem;
import com.ambermount.warehouse.inventory_service.exception.NotFoundException;
import com.ambermount.warehouse.inventory_service.repo.InventoryRepository;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;



@Service
public class InventoryService {

    private final InventoryRepository repo;

    public InventoryService(InventoryRepository repo)
    {
        this.repo = repo;
    }

    @Transactional
    public InventoryServiceResponse create(InventoryServiceRequest req)
    {
        String sku = req.getSku().trim();
        InventoryItem i = repo.findBySku(sku)
               .orElseGet(() -> new InventoryItem(sku, 0));

        i.setQuantity(req.getQuantity());

        InventoryItem saved = repo.save(i);
        return new InventoryServiceResponse(saved.getSku(), saved.getQuantity());
    }

    @Transactional(readOnly = true)
    public InventoryServiceResponse getInventoryBySKu(String sku)
    {
        InventoryItem i = repo.findBySku(sku.trim())
                     .orElseThrow(() -> new NotFoundException("SKU not found in Inventory: " + sku)) ;   
                     
         return new InventoryServiceResponse(i.getSku(), i.getQuantity());            
    }

    @Transactional(readOnly = true)
    public List<InventoryServiceResponse> getInventory()
    {
        return  repo.findAll(Sort.by("sku")).stream()
                        .map(item -> new InventoryServiceResponse(item.getSku(), item.getQuantity()))
                        .toList();         
    }
    
    @Transactional
    public InventoryServiceResponse patchBySKu(String sku, InventoryPatchRequest req)
    {
        InventoryItem i = repo.findBySku(sku.trim())
                 .orElseThrow(() -> new NotFoundException("SKU not found in Inventory for Update: " + sku)) ;

        i.setQuantity(req.getQuantity());
        InventoryItem saved = repo.save(i);
        return new InventoryServiceResponse(saved.getSku(), saved.getQuantity());

    }

    @Transactional
     public InventoryServiceResponse deleteBySku(String sku)
     {
        InventoryItem i = repo.findBySku(sku.trim())
                 .orElseThrow(() -> new NotFoundException("SKU not found in Inventory for deletion: " + sku)) ;
        InventoryItem deleted = repo.deleteBySku(sku);
        return new InventoryServiceResponse(deleted.getSku(), deleted.getQuantity());

    }
}
