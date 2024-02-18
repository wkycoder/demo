package com.wky.demo.interview.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wuming
 * @date 2024/1/5/01/05 15:18
 */
@Component
public class B {

    @Autowired
    private A a;


}
