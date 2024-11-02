package com.wky.demo.design.strategy.spring;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wuming
 * @date 2024/6/15/06/15 19:30
 */
@Getter
@AllArgsConstructor
public enum HandlerEnum {

    /**
     * 处理器枚举
     */
    DEMO(1, "demo");

    private Integer code;

    private String handler;


    public static String getHandler(Integer code) {
        for (HandlerEnum handler : HandlerEnum.values()) {
            if (handler.getCode().equals(code)) {
                return handler.getHandler();
            }
        }
        return null;
    }


}
