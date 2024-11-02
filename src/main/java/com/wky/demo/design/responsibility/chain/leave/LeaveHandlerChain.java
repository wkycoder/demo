package com.wky.demo.design.responsibility.chain.leave;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 处理器链
 *
 * @author wangkunyang
 * @date 2023/04/07 10:47
 */
@Component
public class LeaveHandlerChain {

//    @Resource
    private List<LeaveHandler> handlers;

    /**
     * 业务类
     */

    public void addHandler(LeaveHandler handler) {
        handlers.add(handler);
    }

    /**
     * 处理请求
     * @param request
     */
    public void handle(LeaveRequest request) {
        for (LeaveHandler handler : handlers) {
            // 获取处理结果
            boolean result = handler.handle(request);
        }
    }






}
