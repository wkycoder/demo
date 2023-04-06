package com.wky.demo.responsibility.chain.list;

/**
 * @author wangkunyang
 * @date 2023/04/06 15:11
 */
public class HandlerA extends Handler {

    @Override
    protected boolean doHandle() {
        System.out.println("进入HandlerA");
        boolean handled = false;
        // ... 判读是否需要处理
        return handled;
    }

}
