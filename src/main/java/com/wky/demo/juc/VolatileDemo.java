package com.wky.demo.juc;

import java.util.concurrent.TimeUnit;

/**
 * @author wangkunyang
 * @date 2023/04/21 13:26
 */
public class VolatileDemo {

    int a = 0;
    boolean flag = false;


    public void write() {
        a = 1;
        flag = true;
    }

    public void read() {
        if (flag) {
            System.out.println(a);
        }
    }


    public static void main(String[] args) throws InterruptedException {
        // 新建两个线程A和B
        // A修改a的值，B进行读取
        VolatileDemo demo = new VolatileDemo();
        Thread a = new Thread(() -> {
            demo.write();
        });
        Thread b = new Thread(() -> {
            demo.read();
        });
        a.start();
        TimeUnit.SECONDS.sleep(2);
        b.start();
    }





}
