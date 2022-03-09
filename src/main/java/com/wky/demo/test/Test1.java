package com.wky.demo.test;

import java.text.DecimalFormat;

/**
 * @author: wangkunyang
 * @date 2021/09/13 12:57
 */
public class Test1 {

    public static void main(String[] args) {
        DecimalFormat format = new DecimalFormat("#.00");
//        BigDecimal decimal = new BigDecimal();
        String format1 = format.format(null);
        System.out.println(format1);
//        String result = "";
//        for (int i = 1; i <= 5000 ; i++) {
//            result += "(9," + i + "),";
//        }
//        System.out.println(result);
//        List<Integer> data = new ArrayList<>();
//        List<Integer> collect = data.stream().map(Integer::intValue).collect(Collectors.toList());
//        System.out.println("xxxx");
    }
}
