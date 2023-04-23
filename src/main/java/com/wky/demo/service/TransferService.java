package com.wky.demo.service;

import com.wky.demo.model.req.TransferReq;

import java.math.BigDecimal;

/**
 * @author: wangkunyang
 * @date 2021/08/30 17:19
 */
public interface TransferService {

    /**
     * 转账
     * @param transferReq
     */
    void transfer(TransferReq transferReq);

    void transfer2(TransferReq transferReq);


    /**
     * 指定用户的账户减去指定的金额
     * @param username
     * @param amount
     */
    void add(String username, BigDecimal amount);

    /**
     * 测试事务提交后进行一些操作
     */
    void testCommitted();

    /**
     * 测试事务失效
     */
    void testTxInvalid();

    /**
     * 捕获异常
     */
    void catchException();
}
