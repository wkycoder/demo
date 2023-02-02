package com.wky.demo.pair;

import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

/**
 * @author wangkunyang
 * @date 2023/02/02 15:10
 */
public class Demo {

    public static void main(String[] args) {

        // ImmutablePair
        Pair<String, String> pair = Pair.of("left", "right");
        System.out.println(pair.getLeft());
        System.out.println(pair.getRight());
        // UnsupportedOperationException
//        pair.setValue("2");

        // Pair有两个实现类 ImmutablePair和 MutablePair  value可变和不可变
        Pair<Integer, String> mutablePair = MutablePair.of(1, "ddd");
        mutablePair.setValue("1");
        System.out.println(mutablePair.getValue());
    }

}
