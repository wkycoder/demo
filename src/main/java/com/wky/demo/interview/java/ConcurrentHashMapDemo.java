package com.wky.demo.interview.java;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wuming
 * @date 2024/3/14/03/14 14:42
 */
public class ConcurrentHashMapDemo {


    public static void main(String[] args) {
        Map<String, Integer> map = new ConcurrentHashMap<>();
        map.put("aaa", 1);
        /**
         * jdk1.7 底层是分段锁（Segment<K,V> extends ReentrantLock），里面有一个HashEntry数组
         * 初始化的时候，会创建Segment数组（大小为16）以及Segment[0]，里面HashEntry数组的初始容量是2
         * 往map中添加元素的时候，会先计算key的hashcode，然后根据hashcode和Segment数组长度取模，得到应该放在哪个
         * 段（Segment）中
         *
         * jdk1.8 底层是分段锁思想+CAS+synchronized，没有元素则先使用CAS添加，有元素则使用synchronized加锁
         *
         */

    }






}
