package com.wky.demo.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangkunyang
 * @date 2022/09/27 13:48
 */
public class ForRemoveTest {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        for (String s : list) {
            if ("2".equals(s)) {
                list.remove(s);
            }
        }
        for (int i = 0; i < list.size(); i++) {
            list.remove(list.get(i));
        }

        for (String s : list) {
            System.out.println(s);
        }
//        if ("2".equals(s)) {
//            list.remove(s);
//        }

//        Iterator<String> iterator = list.iterator();
//        while (iterator.hasNext()) {
//            System.out.println(iterator.next());
//        }


    }

}
