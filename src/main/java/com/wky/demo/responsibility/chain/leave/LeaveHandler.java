package com.wky.demo.responsibility.chain.leave;

/**
 * 请假处理器
 * @author wangkunyang
 * @date 2023/04/07 10:46
 */
public abstract class LeaveHandler {


    /**
     * 处理请假请求
     *
     * @param request
     * @return
     */
    public abstract boolean handle(LeaveRequest request);


}
