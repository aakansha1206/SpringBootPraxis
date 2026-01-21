package com.ambermount.common_observability.correlation;

public class CorrelationIdConstants {

    private CorrelationIdConstants()
    {

    }
    
    public static final String DEFAULT_HEADER = "X-Correlation-Id";
    public static final String MDC_KEY        = "correlationId";
}
