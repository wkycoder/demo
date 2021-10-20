package com.wky.demo.strategy;

/**
 * 使用LFU算法进行淘汰
 * @author: wangkunyang
 * @date 2021/10/19 11:33
 */
public class LfuEvictionStrategy implements EvictionStrategy {

    @Override
    public void evictCache() {
        System.out.println("LFU eviction strategy");
    }
}
