package com.wky.demo.design.responsibility.chain.list;

/**
 * @author wangkunyang
 * @date 2023/04/06 15:16
 */
public class HandlerChain {

    private Handler head;
    private Handler tail;

    public void addHandler(Handler handler) {
        if (head == null) {
            head = handler;
            tail = handler;
            return;
        }
        tail.setNext(handler);
        tail = handler;
    }

    public void handle() {
        if (head != null) {
            head.handle();
        }
    }



}
