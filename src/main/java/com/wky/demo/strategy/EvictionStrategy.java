package com.wky.demo.strategy;

/**
 * Redis的淘汰策略
 * @author: wangkunyang
 * @date 2021/10/19 11:24
 */
public interface EvictionStrategy {

    /**
     * 淘汰缓存
     */
    void evictCache();

}
