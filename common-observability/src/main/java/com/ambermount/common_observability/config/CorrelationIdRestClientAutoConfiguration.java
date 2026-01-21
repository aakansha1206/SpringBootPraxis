package com.ambermount.common_observability.config;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestClientCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;

import com.ambermount.common_observability.correlation.CorrelationIdProperties;
import com.ambermount.common_observability.correlation.CorrelationIdRestClientInterceptor;

@AutoConfiguration
@ConditionalOnClass(RestClient.class)
@EnableConfigurationProperties(CorrelationIdProperties.class)
@ConditionalOnProperty(
       prefix = "common.observability.correlation",
       name   = "enabled",
       havingValue = "true",
       matchIfMissing = true
)
public class CorrelationIdRestClientAutoConfiguration {

    @Bean
    public CorrelationIdRestClientInterceptor correlationIdRestClientInterceptor(CorrelationIdProperties props)
    {
         return new CorrelationIdRestClientInterceptor(props);
    }

    @Bean
    public RestClientCustomizer correlationIdClientCustomizer(CorrelationIdRestClientInterceptor interceptor)
    {
        return  builder -> builder.requestInterceptor(interceptor);
    }
    
    
}
