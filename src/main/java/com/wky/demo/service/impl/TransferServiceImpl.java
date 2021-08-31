package com.wky.demo.service.impl;

import com.wky.demo.exception.GlobalRuntimeException;
import com.wky.demo.model.entity.AccountEntity;
import com.wky.demo.model.req.TransferReq;
import com.wky.demo.repository.AccountRepository;
import com.wky.demo.service.OperateService;
import com.wky.demo.service.TransferService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * @author: wangkunyang
 * @date 2021/08/30 17:20
 */
@Slf4j
@Service
public class TransferServiceImpl implements TransferService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private OperateService operateService;

    /**
     * 转账
     * Transaction silently rolled back because it has been marked as rollback-only
     *
     * @param transferReq
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void transfer(TransferReq transferReq) {
        String source = transferReq.getFrom();
        String target = transferReq.getTo();
        BigDecimal amount = transferReq.getAmount();
        try {
            // requires_new : 创建一个新事务，并暂停当前事务，不会并行执行
            // add方法会自己新开一个事务，执行完这一行add方法的事务就提交了
            // transfer方法抛异常导致事务回滚并不会影响到add方法（事务已提交）
            operateService.add(target, amount);
            // sub方法和transfer方法在同一个事务中
            operateService.sub(source, amount);
            int i = 1 / 0;
        } catch (Exception e) {
            log.error("转账操作出现异常，事务开始回滚", e);
            throw new GlobalRuntimeException(500, "转账失败");
        }
    }

    /**
     * 一个方法使用this调用同一个service中的另一个方法，会导致事务注解失效
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
        accountRepository.save(accountEntity);
    }

}
