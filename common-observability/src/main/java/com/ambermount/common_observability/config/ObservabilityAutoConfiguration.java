package com.ambermount.common_observability.config;

import com.ambermount.common_observability.aop.ExecutionTimeLoggingAspect;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@AutoConfiguration
@ConditionalOnProperty(
       prefix = "common.observability.exec-time",
       name   = "enabled",
       havingValue = "true",
       matchIfMissing = true
)
@EnableAspectJAutoProxy
public class ObservabilityAutoConfiguration {

    @Bean
    public ExecutionTimeLoggingAspect executionTimeLoggingAspect()
    {
        return new ExecutionTimeLoggingAspect();
    }
    
}

