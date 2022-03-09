package com.wky.demo.strategy;

/**
 * @author: wangkunyang
 * @date 2021/11/14 12:49
 */
public class EvictionStrategyFactory {

    public static EvictionStrategy getEvictionStrategy(String type) {
        if ("lru".equals(type)) {
            return new LruEvictionStrategy();
        }

        return null;
    }
}
