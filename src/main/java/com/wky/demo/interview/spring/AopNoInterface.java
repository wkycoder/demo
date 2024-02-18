package com.wky.demo.interview.spring;

import org.springframework.stereotype.Component;
import javax.transaction.Transactional;

/**
 * @author wuming
 * @date 2024/1/6/01/06 20:16
 */
@Component
public class AopNoInterface {


    @Transactional
    public void test() {

    }



}
