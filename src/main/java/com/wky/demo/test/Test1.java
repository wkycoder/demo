package com.wky.demo.test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: wangkunyang
 * @date 2021/09/13 12:57
 */
public class Test1 {

    public static void main(String[] args) {
        String result = "";
        for (int i = 1; i <= 5000 ; i++) {
            result += "(9," + i + "),";
        }
        System.out.println(result);
        List<Integer> data = new ArrayList<>();
        List<Integer> collect = data.stream().map(Integer::intValue).collect(Collectors.toList());
        System.out.println("xxxx");
    }
}
