package com.wky.demo.service;

import com.wky.demo.model.vo.ExcelVO;

import java.util.List;

/**
 * @author: wangkunyang
 * @date 2021/08/27 11:16
 */
public interface DemoService {

    /**
     * 处理数据
     * @param list
     */
    void processData(List<ExcelVO> list);

    /**
     * 抢红包
     * @param redId
     */
    Integer grabRed(Integer redId) throws InterruptedException;

    /**
     * 测试requires_new
     */
    void testRN();

    /**
     * 测试手动捕获异常
     */
    void testNT();

    /**
     * methodA
     */
    void methodA();

    /**
     * methodB
     */
    void methodB();

    /**
     * 测试缓存
     */
    void testCache();

}
