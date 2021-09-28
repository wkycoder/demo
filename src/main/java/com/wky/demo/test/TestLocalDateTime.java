package com.wky.demo.test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * @author: wangkunyang
 * @date 2021/09/24 09:57
 */
public class TestLocalDateTime {

    public static void main(String[] args) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime localDateTime1 = localDateTime.withHour(23).withMinute(59).withSecond(59);
        System.out.println(df.format(localDateTime1));

        ArrayList<Integer> arrayList = new ArrayList<>();
        ArrayList<Integer> arrayList1 = new ArrayList<>();
        arrayList.addAll(arrayList1);
        System.out.println(arrayList.size());

    }

}
