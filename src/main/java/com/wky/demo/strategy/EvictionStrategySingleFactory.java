package com.wky.demo.strategy;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * EvictionStrategySingleFactory.getEvictionStrategy
 * @author: wangkunyang
 * @date 2021/10/19 11:39
 */
public class EvictionStrategySingleFactory implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    /**
     * key可以是枚举类型、也可以是字符串类型
     */
    private static Map<EvictionType, EvictionStrategy> evictionStrategyMap = new HashMap<>();

    static {
        // 可以静态导入枚举
        evictionStrategyMap.put(EvictionType.LRU, new LfuEvictionStrategy());
        evictionStrategyMap.put(EvictionType.LFU, new LfuEvictionStrategy());
        evictionStrategyMap.put(EvictionType.FIFO, new LfuEvictionStrategy());
    }

    @PostConstruct
    public void init() {
        Map<String, EvictionStrategy> beans = applicationContext.getBeansOfType(EvictionStrategy.class);
        if (!beans.isEmpty()) {
            beans.values().forEach(bean -> {
                evictionStrategyMap.put(null, null);
            });
        }
    }

    public static EvictionStrategy getEvictionStrategy(EvictionType evictionType) {
        return evictionStrategyMap.get(evictionType);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
