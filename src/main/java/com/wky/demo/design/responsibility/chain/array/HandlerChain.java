package com.wky.demo.design.responsibility.chain.array;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * 处理器链持有了所有的处理器，并支持添加新的处理器
 * 框架中的实际应用，名字不一定都叫XxxChain
 *
 * @see org.springframework.web.servlet.HandlerInterceptor
 *
 * @author wangkunyang
 * @date 2023/04/06 15:41
 */
@Component
public class HandlerChain {

    // 使用注入模式可以借助@Order注解保证顺序
//    @Resource
    private List<Handler> handlers;

    /**
     * 可以有业务service
     */

    @PostConstruct
    public void init() {

    }

    public void addHandler(Handler handler) {
        if (CollectionUtils.isNotEmpty(handlers)) {
            handlers = new ArrayList<>();
        }
        handlers.add(handler);
    }

    /**
     * 命中就立即终止
     * 变体是命中不终止，继续遍历
     */
    public void handle() {
        // 处理请求
        for (Handler handler : handlers) {
            boolean handled = handler.handle();
            // 职责链模式有一种变体，即请求会被所有的处理器都处理一遍，不存在中途终止的情况
            // 可以不用break
            if (handled) {
                break;
            }
        }
        // 验证都通过则可以去执行业务逻辑
        // SpringMVC的拦截器就是这么实现的
        //

        // 处理响应

    }

}
