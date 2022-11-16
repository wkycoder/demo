package com.wky.demo.test;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author: wangkunyang
 * @date 2021/12/31 15:17
 */
public class TestTarget {


    public static void main(String[] args) {

        BigDecimal salesAmount = BigDecimal.valueOf(33);
        BigDecimal targetSalesAmount = BigDecimal.valueOf(33);
        BigDecimal result = salesAmount.divide(targetSalesAmount, 4, RoundingMode.HALF_UP);
        System.out.println(result);

        Integer a = null;
        String s = String.valueOf(a);
        System.out.println(s);


        String str = null;
        String replace = str.replace("ddddeeee", null).replace("fff", "uuu");
        System.out.println(replace);
    }

}
