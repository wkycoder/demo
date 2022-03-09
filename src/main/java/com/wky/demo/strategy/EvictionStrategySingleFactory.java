package com.wky.demo.strategy;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * EvictionStrategySingleFactory.getEvictionStrategy
 * 获取到的是单例的策略模式，且使用Map去维护策略对象
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
        // 使用Map去维护映射关系有一个缺点，就是当我们新增一个策略的时候需要修改代码，违反了开闭原则
        // 因此我们可以使用List去维护映射关系
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
