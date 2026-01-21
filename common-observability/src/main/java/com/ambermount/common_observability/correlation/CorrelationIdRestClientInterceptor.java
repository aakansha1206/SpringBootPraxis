package com.ambermount.common_observability.correlation;

import java.io.IOException;
import java.util.UUID;

import org.slf4j.MDC;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

public class CorrelationIdRestClientInterceptor implements ClientHttpRequestInterceptor {
    private final CorrelationIdProperties props;

    public CorrelationIdRestClientInterceptor(CorrelationIdProperties props)
    {
        this.props = props;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request,
                                         byte[] body, 
                                        ClientHttpRequestExecution execution)
            throws IOException {

          String headerName = props.getHeaderName();
      
        if (headerName == null || headerName.isBlank())
        {
            headerName = "X-Correlation-Id";
        }

        String mdcKey = props.getMdcKey();
        if(mdcKey == null || mdcKey.isBlank())
        {
            mdcKey = "correlationId";
        }
        
        String correlationId = MDC.get(mdcKey);
        if(correlationId == null || correlationId.isBlank())
        {
           correlationId = UUID.randomUUID().toString();
           MDC.put(mdcKey, correlationId);  
        }
        request.getHeaders().set(headerName, correlationId);
        return execution.execute(request, body);
    }

    
}
