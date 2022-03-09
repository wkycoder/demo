package com.wky.demo.strategy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author: wangkunyang
 * @date 2021/10/19 11:48
 * 循环获取, 减少类的改动, 反射
 * 循环去判断使用哪一种策略
 */
public class EvictionStrategyLoopFactory {

    private static List<EvictionRange> evictionRangeList = new ArrayList<>(6);

    static {
        evictionRangeList.add(new EvictionRange(EvictionType.LRU, LruEvictionStrategy.class));
        evictionRangeList.add(new EvictionRange(EvictionType.LFU, LfuEvictionStrategy.class));
        evictionRangeList.add(new EvictionRange(EvictionType.FIFO, FifoEvictionStrategy.class));
    }

    public static EvictionStrategy getEvictionStrategy(String evictionType) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        EvictionStrategy evictionStrategy = null;
        for (EvictionRange evictionRange : evictionRangeList) {
            if (evictionRange.select(evictionType)) {
                Class evictionStrategyClass = evictionRange.getEvictionStrategyClass();
                Constructor constructor = evictionStrategyClass.getConstructor();
                evictionStrategy = (EvictionStrategy) constructor.newInstance();
            }
        }
        return evictionStrategy;
    }

}
