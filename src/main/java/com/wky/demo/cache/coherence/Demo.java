package com.wky.demo.cache.coherence;

/**
 * @author wangkunyang
 * @date 2023/01/13 14:16
 */
public class Demo {

    private int a = 0;
    private int b = 0;

    public void test1() {
        a = 1;
        b = a + 1;
        assert b == 2;
    }




}
