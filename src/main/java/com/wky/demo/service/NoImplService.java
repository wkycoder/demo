package com.wky.demo.service;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author wuming
 * @date 2023/11/4/11/04 21:14
 */
@Service
public class NoImplService {


    @Transactional(rollbackOn = Exception.class)
    public void test() {


    }





}
