package com.wky.demo.interview.java;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wuming
 * @date 2024/3/19/03/19 18:38
 */
public class AqsDemo {

    public static void main(String[] args) {

        ReentrantLock lock = new ReentrantLock();
        lock.lock();

    }
}
