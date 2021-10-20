package com.wky.demo.strategy;

/**
 * 获取到的策略对象是多例的
 * @author: wangkunyang
 * @date 2021/10/19 11:48
 */
public class EvictionStrategyPrototypeFactory {

    public static EvictionStrategy getEvictionStrategy(EvictionType evictionType) {
        if (evictionType == null) {
            throw new RuntimeException("evictionType should not be null");
        }

        if (evictionType == EvictionType.LRU) {
            return new LruEvictionStrategy();
        } else if (evictionType == EvictionType.LFU) {
            return new LfuEvictionStrategy();
        } else if (evictionType == EvictionType.FIFO) {
            return new FifoEvictionStrategy();
        } else {
            throw new RuntimeException("the evictionType is not support");
        }
    }

}
