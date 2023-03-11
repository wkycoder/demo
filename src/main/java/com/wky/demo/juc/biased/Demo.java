package com.wky.demo.juc.biased;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author wuming
 * @date 2023/3/3/03/03 16:30
 */
public class Demo {

    public static void main(String[] args) throws InterruptedException {

        // JVM默认会延迟几秒钟去开启偏向锁，我们使用参数关闭延迟：-XX:BiasedLockingStartupDelay=0
//        TimeUnit.SECONDS.sleep(5);

        Object lock = new Object();
        // 无锁
        System.out.println(ClassLayout.parseInstance(lock).toPrintable());

        synchronized (lock) {
            System.out.println(ClassLayout.parseInstance(lock).toPrintable());
        }


    }


}
