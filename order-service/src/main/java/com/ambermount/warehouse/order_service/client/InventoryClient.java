package com.ambermount.warehouse.order_service.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

@Component
public class InventoryClient {
    
    private final RestClient restClient;
    private final String baseUrl;

    public InventoryClient(RestClient restClient, @Value("${services.inventory.base-url}") String baseUrl)
    {
         this.restClient = restClient;
         this.baseUrl    = baseUrl;

    } 
  
    public Integer getAvailableInventory(String sku)
    {
        try
        {
           InventoryResponse res =  restClient.get()
                   .uri(baseUrl + "/api/v1/inventory/sku/{sku}",sku)
                   .retrieve()
                   .body(InventoryResponse.class);

            return res == null ? null : res.getQuantity();     


        }catch(RestClientResponseException  ex)
        {
            if(ex.getStatusCode() == HttpStatus.NOT_FOUND)
            {
                return null;
            }
            throw ex;
        }
    }

    
    public static class InventoryResponse{
    
        private String sku;

        private Integer quantity;

        public String getSku() {return sku;}

        public Integer getQuantity() { return quantity;}
    }



}
