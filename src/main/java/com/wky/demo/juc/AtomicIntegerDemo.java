package com.wky.demo.juc;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wangkunyang
 * @date 2023/03/01 12:58
 */
public class AtomicIntegerDemo {


    public static void main(String[] args) {
        // private volatile int value;
        AtomicInteger atomicInteger = new AtomicInteger();

        /**
         * 原子变量 （atomic variable）, 核心是CAS原子操作
         */
        int i = atomicInteger.incrementAndGet();

        System.out.println(i);

    }

}
