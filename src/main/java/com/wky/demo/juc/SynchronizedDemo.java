package com.wky.demo.juc;

/**
 * @author wangkunyang
 * @date 2023/03/01 13:13
 */
public class SynchronizedDemo {

    private int i = 1;

    public void test1() {
        synchronized (this) {
            i++;
        }
    }

    public synchronized void test2() {
        i++;
    }



}
