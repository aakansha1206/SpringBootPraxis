package com.ambermount.warehouse.order_service.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

@Component
public class CatalogClient {
    
    private final RestClient restClient;
    private final String baseUrl;

    public CatalogClient(RestClient restClient, @Value("${services.catalog.base-url}") String baseUrl)
    {
         this.restClient = restClient;
         this.baseUrl    = baseUrl;

    }   
    
    public CatalogProductResponse getProduct(String sku)
    {
        try
        {
            return restClient.get()
                   .uri(baseUrl + "/api/v1/products/sku/{sku}", sku)
                   .retrieve()
                   .body(CatalogProductResponse.class);

        }catch(RestClientResponseException  ex)
        {
            if(ex.getStatusCode() == HttpStatus.NOT_FOUND)
            {
                return null;
            }
            throw ex;
        }
    }
    

}
