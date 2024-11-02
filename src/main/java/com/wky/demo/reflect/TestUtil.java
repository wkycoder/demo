package com.wky.demo.reflect;

/**
 * @author wuming
 * @date 2024/5/18/05/18 20:47
 */
public class TestUtil {


    private static String name;

    private static final String address;

    static {
        address = "abc";
    }

    public static String getName() {
        return name;
    }

    public static String getAddress() {
        return address;
    }

}
