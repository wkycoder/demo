package com.wky.demo.ratelimiter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Api限流器
 * @author wuming
 * @date 2023/3/19/03/19 21:16
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ApiRateLimit {








}
