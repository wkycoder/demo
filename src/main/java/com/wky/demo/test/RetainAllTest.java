package com.wky.demo.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangkunyang
 * @date 2022/11/08 15:38
 */
public class RetainAllTest {

    public static void main(String[] args) {
        List<Integer> a = new ArrayList<>();
        a.add(1);
        a.add(2);
        List<Integer> b = new ArrayList<>();
        b.add(3);
        b.add(4);
        a.retainAll(b);
        List<Integer> c = new ArrayList<>();
        a.removeAll(c);
        System.out.println(a);

    }

}
