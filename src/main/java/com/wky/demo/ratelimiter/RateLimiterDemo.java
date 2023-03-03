package com.wky.demo.ratelimiter;

import com.google.common.util.concurrent.RateLimiter;

/**
 * @author wuming
 * @date 2023/2/18/02/18 14:13
 */
public class RateLimiterDemo {

    public static void main(String[] args) {
        RateLimiter rateLimiter = RateLimiter.create(1);
        boolean b = rateLimiter.tryAcquire();



    }


}
