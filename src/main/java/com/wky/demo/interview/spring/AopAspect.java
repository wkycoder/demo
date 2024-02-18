package com.wky.demo.interview.spring;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author wuming
 * @date 2024/1/6/01/06 19:36
 */
@Aspect
@Component
public class AopAspect {



    @Around("execution(* com.wky.demo.interview.spring.*.*(..))")
    public void around(ProceedingJoinPoint joinPoint) {
        System.out.println("前置");
        try {
            joinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println("后置");
    }



}
