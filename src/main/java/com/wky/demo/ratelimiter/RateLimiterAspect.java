package com.wky.demo.ratelimiter;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @author wuming
 * @date 2023/3/19/03/19 21:20
 */
@Aspect
@Component
public class RateLimiterAspect {


    @Before("@annotation(apiRateLimit)")
    public void doBefore(JoinPoint joinPoint, ApiRateLimit apiRateLimit) {






    }




}
