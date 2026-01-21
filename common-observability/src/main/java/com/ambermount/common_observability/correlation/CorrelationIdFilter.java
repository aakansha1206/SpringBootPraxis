package com.ambermount.common_observability.correlation;

import java.io.IOException;
import java.util.UUID;

import org.slf4j.MDC;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CorrelationIdFilter extends OncePerRequestFilter {

    private final CorrelationIdProperties props;

    public CorrelationIdFilter(CorrelationIdProperties props)
    {
        this.props = props;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request)
    {
        String path = request.getRequestURI();
        return path.startsWith("/swagger-ui")
              || path.startsWith("/v3/api-docs")
              || path.startsWith("/favicon.ico");
    }

    @Override
    protected void doFilterInternal(
                    HttpServletRequest request, 
                    HttpServletResponse response, 
                    FilterChain filterChain) throws ServletException, IOException 
    {
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
        
        String correlationId = request.getHeader(headerName);
        if((correlationId == null || correlationId.isBlank()) && props.isGenerateIfMissing())
        {
             correlationId = UUID.randomUUID().toString();
        }

   
            MDC.put(CorrelationIdConstants.MDC_KEY, correlationId);
            response.setHeader(headerName, correlationId);

        

        try
        {
           filterChain.doFilter(request, response);
        }finally{
            MDC.remove(CorrelationIdConstants.MDC_KEY);
        }
        
    }


    
}
