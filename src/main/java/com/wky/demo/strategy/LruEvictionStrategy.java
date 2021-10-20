package com.wky.demo.strategy;

/**
 * 使用LRU算法进行淘汰
 * @author: wangkunyang
 * @date 2021/10/19 11:33
 */
public class LruEvictionStrategy implements EvictionStrategy {

    @Override
    public void evictCache() {
        System.out.println("LRU eviction strategy");
    }
}
