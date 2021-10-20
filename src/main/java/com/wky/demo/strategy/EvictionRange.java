package com.wky.demo.strategy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: wangkunyang
 * @date 2021/10/19 13:30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EvictionRange {

    private EvictionType evictionType;

    private Class evictionStrategyClass;

    public boolean select(String key) {
        return evictionType.name().equals(key);
    }

}
