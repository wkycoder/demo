package com.wky.demo.service.impl;

import com.wky.demo.model.entity.AccountEntity;
import com.wky.demo.model.entity.SendRecordEntity;
import com.wky.demo.repository.AccountRepository;
import com.wky.demo.repository.SendRecordRepository;
import com.wky.demo.service.OperateService;
import com.wky.demo.service.SendRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.math.BigDecimal;

/**
 * @author: wangkunyang
 * @date 2021/08/30 18:50
 */
@Slf4j
@Service
public class OperateServiceImpl implements OperateService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private SendRecordRepository sendRecordRepository;

    @Autowired
    private SendRecordService sendRecordService;


    /**
     * 指定用户的账户减去指定的金额（扣钱）
     * @param username
     * @param amount
     */
    @Override
    public void sub(String username, BigDecimal amount) {
        log.info("{}的账户-{}元", username, amount);
        AccountEntity accountEntity = accountRepository.findByUsername(username);
        BigDecimal balance = accountEntity.getBalance();
        log.info("{}的账户余额：{}", username, balance);
        accountEntity.setBalance(balance.subtract(amount));
        log.info("扣钱后：{}", accountEntity.getBalance());
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
        log.info("{}的账户+{}元", username, amount);
        AccountEntity accountEntity = accountRepository.findByUsername(username);
        BigDecimal balance = accountEntity.getBalance();
        log.info("{}的账户余额：{}", username, balance);
        accountEntity.setBalance(balance.add(amount));
        log.info("加钱后：{}", accountEntity.getBalance());
        accountRepository.saveAndFlush(accountEntity);
//        try {
//
////            int i = 1/0;
//        } catch (Exception e) {
//            log.error("add方法出现异常", e);
//            // 此处抛出异常，外层调用方会认为事务需要回滚，即便我们在外层catch住这个异常，事务仍然会悄悄的回归，因为此时事务的状态已经被改为"rollback-only"
//            throw new GlobalRuntimeException(500, "加钱失败");
//        }
    }

    /**
     * 测试事务提交后再次提交
     * @param accountEntity
     */
    @Override
//    @Transactional(rollbackFor = Exception.class)
    public void testCommitted(AccountEntity accountEntity) {
        log.info("当前事务名：{}", TransactionSynchronizationManager.getCurrentTransactionName());
        try {
            // 修改账户金额
            accountEntity.setBalance(new BigDecimal(100));
        } catch (Exception e) {
            log.error("发生异常", e);
        } finally {
            // 修改账户初始金额
            accountRepository.save(accountEntity);
        }
    }

    @Override
//    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void send(SendRecordEntity sendRecordEntity) {
        log.info("当前事务名：{}", TransactionSynchronizationManager.getCurrentTransactionName());
        String errMsg;
        try {
            // 给用户发消息
            sendMsg(sendRecordEntity.getReceiver(), sendRecordEntity.getContent());
            sendRecordEntity.setStatus(1);
            int i = 1/0;
            // 修改账户金额
        } catch (Exception e) {
            log.error("发生异常", e);
            errMsg = e.getMessage();
            sendRecordEntity.setFailMsg(errMsg);
            sendRecordEntity.setStatus(0);
//            throw new GlobalRuntimeException(500, e.getMessage());
        } finally {
            // 更新发送记录
//            sendRecordRepository.save(sendRecordEntity);
            sendRecordService.updateSendRecord(sendRecordEntity);
        }
    }

    private void sendMsg(String receiver, String content) {
        System.out.println(receiver + " : " + content);
    }

}
