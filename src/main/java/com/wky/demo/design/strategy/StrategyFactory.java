package com.wky.demo.design.strategy;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 策略工厂类：负责创建策略对象
 * @author: wangkunyang
 * @date 2021/11/08 15:39
 */
public class StrategyFactory {

    private static final Map<String, Strategy> strategies = new HashMap<>();

    static {
        strategies.put("A", new ConcreteStrategyA());
        strategies.put("B", new ConcreteStrategyB());
    }

    /**
     * 获取对应类型的策略
     * @param type
     * @return
     */
    public Strategy getStrategy(String type) {
        if (StringUtils.isNotEmpty(type)) {
            throw new IllegalArgumentException("type should not be empty");
        }
        return strategies.get(type);
    }

//    public Strategy getStrategy(String type) {
//        if (StringUtils.isNotEmpty(type)) {
//            throw new IllegalArgumentException("type should not be empty");
//        }
//        if ("A".equals(type)) {
//            return new ConcreteStrategyA();
//        } else if ("B".equals(type)) {
//            return new ConcreteStrategyB();
//        }
//        return null;
//    }

}
