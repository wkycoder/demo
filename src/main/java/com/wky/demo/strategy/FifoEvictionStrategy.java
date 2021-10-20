package com.wky.demo.strategy;

/**
 * FIFO
 * @author: wangkunyang
 * @date 2021/10/19 11:33
 */
public class FifoEvictionStrategy implements EvictionStrategy {

    @Override
    public void evictCache() {
        System.out.println("FIFO eviction strategy");
    }
}
