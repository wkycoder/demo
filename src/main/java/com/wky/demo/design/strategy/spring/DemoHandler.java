package com.wky.demo.design.strategy.spring;

/**
 * @author wuming
 * @date 2024/6/15/06/15 19:25
 */
public class DemoHandler implements Handler {

    @Override
    public void handle() {
        System.out.println("demo handler");
    }

}
