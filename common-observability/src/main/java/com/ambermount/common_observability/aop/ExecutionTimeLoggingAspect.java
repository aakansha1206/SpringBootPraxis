package com.ambermount.common_observability.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.ProceedingJoinPoint;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Aspect
public class ExecutionTimeLoggingAspect {

    @Around(
            "(within(@org.springframework.stereotype.Service *) || " +
            " within(@org.springframework.stereotype.Controller *) || " +
            " within(@org.springframework.web.bind.annotation.RestController *))" + 
            " && execution(* *(..))"
          )
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable
    {
        long start = System.currentTimeMillis();
        try
        { 
              return joinPoint.proceed();
        }
        finally
        {
            long duration = System.currentTimeMillis() - start;
            log.info("[EXECUTION TIME] {} took {} ms",
                joinPoint.getSignature().toShortString(), duration
            );
        }
    
    }
    
}
