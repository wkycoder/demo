package com.wky.demo.service.impl;

import com.wky.demo.model.entity.AccountEntity;
import com.wky.demo.repository.AccountRepository;
import com.wky.demo.service.OperateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * @author: wangkunyang
 * @date 2021/08/30 18:50
 */
@Service
public class OperateServiceImpl implements OperateService {

    @Autowired
    private AccountRepository accountRepository;

    /**
     * 指定用户的账户减去指定的金额（扣钱）
     * @param username
     * @param amount
     */
    @Override
    public void sub(String username, BigDecimal amount) {
        AccountEntity accountEntity = accountRepository.findByUsername(username);
        BigDecimal balance = accountEntity.getBalance();
        accountEntity.setBalance(balance.subtract(amount));
        accountRepository.save(accountEntity);
    }

    /**
     * 指定用户的账户加上指定的金额（加钱）
     * @param username
     * @param amount
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void add(String username, BigDecimal amount) {
        AccountEntity accountEntity = accountRepository.findByUsername(username);
        BigDecimal balance = accountEntity.getBalance();
        accountEntity.setBalance(balance.add(amount));
//        int i = 1/0;
        accountRepository.save(accountEntity);
    }

}
