package com.wky.demo.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wangkunyang
 * @date 2021/10/21 20:17
 */
public class TestAddAll {


    public static void main(String[] args) {
        List<Integer> a = new ArrayList<>();
        a.add(2);
        a.add(3);
        a.add(5);

        List<Integer> b = new ArrayList<>();
        b.addAll(a);

        System.out.println(b);


    }

}
