package com.wky.demo.interview.meituan.third;

import java.util.Arrays;

/**
 * @author wuming
 * @date 2023/8/3/08/03 16:47
 */
public class Application {

    public static void main(String[] args) {
        ValueLoader<Integer> valueLoader = (offset, limit) -> {
            if (offset != 0) {
                return null;
            }
            return Arrays.asList(1, 2, 3);
        };

        LazyIterator<Integer> iterator = new LazyIterator<Integer>(valueLoader);

        for (Integer data : iterator) {
            System.out.println(data);
        }


    }

}
