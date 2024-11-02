package com.wky.demo.design.responsibility.chain.array;

/**
 * @author wangkunyang
 * @date 2023/04/06 15:37
 */
public class TwoHandler extends Handler {

    @Override
    protected boolean handle() {
        System.out.println("进入TwoHandler");
        boolean handled = false;
        // ...
        return handled;
    }

}
