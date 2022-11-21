package com.wky.demo.test;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * 生成随机字符串
 *
 * @author wangkunyang
 * @date 2022/11/21 10:10
 */
public class TestRandom {

    public static void main(String[] args) {
        // 只有字母
        String s = RandomStringUtils.randomAlphabetic(40);
//        System.out.println(s);

        // 字母 + 数字
        String s1 = RandomStringUtils.randomAlphanumeric(40);
        System.out.println(s1);


    }

}
