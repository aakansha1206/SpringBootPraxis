package com.ambermount.common_observability.config;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.ambermount.common_observability.correlation.CorrelationIdProperties;
import com.ambermount.common_observability.correlation.CorrelationIdRestClientInterceptor;
import com.ambermount.common_observability.correlation.CorrelationIdFilter;

@AutoConfiguration
@ConditionalOnClass(jakarta.servlet.Filter.class)
@EnableConfigurationProperties(CorrelationIdProperties.class)
@ConditionalOnProperty(
       prefix = "common.observability.correlation",
       name   = "enabled",
       havingValue = "true",
       matchIfMissing = true
)
public class CorrelationIdAutoConfiguration {

    @Bean
    public CorrelationIdFilter CorrelationIdFilter(CorrelationIdProperties props)
    {
        return new CorrelationIdFilter(props);
    }

    
}
