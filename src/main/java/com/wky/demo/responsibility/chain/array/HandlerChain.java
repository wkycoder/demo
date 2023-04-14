package com.wky.demo.responsibility.chain.array;

import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 处理器链持有了所有的处理器，并支持添加新的处理器
 *
 * @author wangkunyang
 * @date 2023/04/06 15:41
 */
public class HandlerChain {

    private List<Handler> handlers;

    public void addHandler(Handler handler) {
        if (CollectionUtils.isNotEmpty(handlers)) {
            handlers = new ArrayList<>();
        }
        handlers.add(handler);
    }

    public void handle() {
        for (Handler handler : handlers) {
            boolean handled = handler.handle();
            // 职责链模式有一种变体，即请求会被所有的处理器都处理一遍，不存在中途终止的情况
            // 可以不用break
            if (handled) {
                break;
            }
        }
    }

}
