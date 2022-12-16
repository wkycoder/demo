package com.wky.demo.test;

import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangkunyang
 * @date 2022/12/06 09:29
 */
public class TestCollection {

    public static void main(String[] args) {

        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        boolean result = CollectionUtils.containsAll(list1, list2);
        System.out.println(result);

    }

}
