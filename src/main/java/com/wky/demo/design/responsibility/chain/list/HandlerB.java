package com.wky.demo.design.responsibility.chain.list;

/**
 * @author wangkunyang
 * @date 2023/04/06 15:14
 */
public class HandlerB extends Handler {

    @Override
    protected boolean doHandle() {
        System.out.println("进入HandlerB");
        boolean handled = false;
        // ... 判读是否需要处理
        return handled;
    }

}
