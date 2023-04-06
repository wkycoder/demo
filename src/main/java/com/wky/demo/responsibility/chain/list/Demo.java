package com.wky.demo.responsibility.chain.list;

/**
 * 这里是使用链表去存储职责处理器的
 *
 * @author wangkunyang
 * @date 2023/04/06 15:19
 */
public class Demo {

    public static void main(String[] args) {
        HandlerChain chain = new HandlerChain();
        // 构建处理器链
        chain.addHandler(new HandlerA());
        chain.addHandler(new HandlerB());
        // 执行
        chain.handle();
        // do other

    }

}
