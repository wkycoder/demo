package com.wky.demo.responsibility.chain.array;

/**
 * @author wangkunyang
 * @date 2023/04/06 15:37
 */
public class OneHandler extends Handler {

    @Override
    protected boolean handle() {
        System.out.println("进入OneHandler");
        boolean handled = false;
        // ...
        return handled;
    }

}
