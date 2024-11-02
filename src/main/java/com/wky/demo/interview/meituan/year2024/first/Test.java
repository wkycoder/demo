package com.wky.demo.interview.meituan.year2024.first;

import java.util.Arrays;

/**
 * @author wuming
 * @date 2024/4/1/04/01 19:06
 */
public class Test {


    public static void main(String[] args) throws InterruptedException {
        Service service = new Service(new UserService());
        service.get(Arrays.asList(1L, 2L));

    }



}
