package com.wky.demo.design.responsibility.chain.array;

/**
 * 使用数组实现
 * @author wangkunyang
 * @date 2023/04/06 15:49
 */
public class Demo {

    public static void main(String[] args) {
        HandlerChain chain = new HandlerChain();
        chain.addHandler(new OneHandler());
        chain.addHandler(new TwoHandler());
        chain.handle();

    }


}
