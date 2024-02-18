package com.wky.demo.interview.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wuming
 * @date 2024/1/5/01/05 15:17
 */
@Component
public class A {

    @Autowired
    private B b;


}
