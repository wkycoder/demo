package com.wky.demo.interview.java;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wuming
 * @date 2024/3/18/03/18 10:46
 */
public class ExecuteAndSubmitDemo {


    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("1");
            }
        });
        // submit方法有重载，支持传入Runnable或Callable
        // Runnable和Callable都会被包装成FutureTask对象，然后执行FutureTask的run方法
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("2");
            }
        });
        executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return null;
            }
        } );
        // FutureTask实现了Runnable接口，内部又包装类了Callable对象，所以FutureTask可以作为Runnable对象执行
    }



}
