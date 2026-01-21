package com.ambermount.common_observability.correlation;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "common.observability.correlation")
public class CorrelationIdProperties 
{
    //Enable correlation id filter 
    private boolean enabled = true;

    //Header name to read/write correlation id 
    private String headerName = CorrelationIdConstants.DEFAULT_HEADER;

    // If request doesnt have header , generate new ID
    private boolean generateIfMissing = true;

    private String mdcKey = "correlationId";

    public boolean isEnabled()
    {
        return enabled;
    }
    public void setEnabled(boolean enabled)
    {
        this.enabled = enabled;
    }


    public String getHeaderName()
    {
        return headerName;
    }
    public void setHeaderName(String headerName)
    {
        this.headerName = headerName;
    }


    public boolean isGenerateIfMissing()
    {
        return generateIfMissing;
    }
    public void setGenerateIfMissing(boolean generateIfMissing)
    {
        this.generateIfMissing = generateIfMissing;
    }

    public String getMdcKey()
    {
        return mdcKey;
    }
    public void setMdcKey(String mdcKey)
    {
        this.mdcKey = mdcKey;
    }
}
