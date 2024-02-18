package com.wky.demo.interview.spring;

import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

/**
 * @author wuming
 * @date 2024/1/6/01/06 19:33
 */
@Component
public class AopImpl implements  AopInterface {


    @Override
    @Transactional
    public void test() {

    }

}
