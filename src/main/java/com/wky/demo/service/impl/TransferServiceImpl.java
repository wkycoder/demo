package com.wky.demo.service.impl;

import com.wky.demo.model.entity.AccountEntity;
import com.wky.demo.model.entity.SendRecordEntity;
import com.wky.demo.model.req.TransferReq;
import com.wky.demo.repository.AccountRepository;
import com.wky.demo.repository.SendRecordRepository;
import com.wky.demo.service.OperateService;
import com.wky.demo.service.TransferService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
    private SendRecordRepository sendRecordRepository;

    @Autowired
    private OperateService operateService;


    /**
     * 转账
     * Transaction silently rolled back because it has been marked as rollback-only
     * 前提：方法A和方法B处于同一个事务
     * 方法A调用方法B，方法B抛出异常并进行回滚，但是被方法A捕获（catch），并没有进行抛出操作，只是打印了一下日志
     * 方法A继续执行，但是此时事务状态已经改为rollback-only，最后在进行事务提交的时候会进行回滚操作并抛出上面的异常信息。[加钱失败，扣钱失败]
     * 如果方法B的事务传播行为是requires_new，此时方法B和方法A是两个不同的事务，方法B抛出异常被方法A捕获没抛出，（方法B回滚）
     * 但是并不会影响到方法A的事务，方法A会继续执行并顺利提交事务。[加钱失败，扣钱成功]
     * 一个方法内部抛出的异常被手动捕获而不做任何处理，这样就会导致spring的事务失效。
     * 事务场景中，抛出异常被catch住，如果需要回滚，一定要手动回滚。
     * @param transferReq
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void transfer(TransferReq transferReq) {
        log.info("开始转账");
        String source = transferReq.getFrom();
        String target = transferReq.getTo();
        BigDecimal amount = transferReq.getAmount();
        try {
            // requires_new : 创建一个新事务，并暂停当前事务，不会并行执行
            // add方法会自己新开一个事务，执行完这一行add方法的事务就提交了
            // transfer方法抛异常导致事务回滚并不会影响到add方法（事务已提交）
            try{
                operateService.add(target, amount);
            } catch (Exception e) {

            }
            // sub方法和transfer方法在同一个事务中
            operateService.sub(source, amount);
//            int i = 1 / 0;
            log.info("转账操作执行完毕");
        } catch (Exception e) {
            // 把异常catch住什么都不做，这样就不会导致事务回滚
            log.error("转账操作出现异常", e);
//            throw new GlobalRuntimeException(500, "转账失败");
        }
    }

    /**
     * 一个方法使用this调用同一个service中的另一个方法，会导致事务注解失效
     * 解决方法：将这个方法抽离到另一个service中，通过注入service去调用该方法
     * 指定用户的账户加上指定的金额（加钱）
     * @param username
     * @param amount
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void add(String username, BigDecimal amount) {
        log.info("add transaction name：{}", TransactionSynchronizationManager.getCurrentTransactionName());
        AccountEntity accountEntity = accountRepository.findByUsername(username);
        BigDecimal balance = accountEntity.getBalance();
        accountEntity.setBalance(balance.add(amount));
        accountRepository.save(accountEntity);
    }

    /**
     * 测试提交后进行其他操作
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void testCommitted() {
        log.info("测试开始");
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setUsername("wky");
        accountEntity.setBalance(new BigDecimal(0));
        accountRepository.save(accountEntity);
        SendRecordEntity sendRecordEntity = new SendRecordEntity();
        sendRecordEntity.setReceiver(accountEntity.getUsername());
        sendRecordEntity.setContent("账号创建成功");
        sendRecordEntity.setCreatedAt(LocalDateTime.now());
        sendRecordEntity.setUpdatedAt(LocalDateTime.now());
        sendRecordRepository.save(sendRecordEntity);
        String currentTransactionName = TransactionSynchronizationManager.getCurrentTransactionName();
        log.info("当前事务名：{}", currentTransactionName);
        // 当前方法必须要有事务，否则会抛出Transaction synchronization is not active
        // 判断当前方法是否存在事务
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
            /**
             * 类似于回调
             */
            @Override
            public void afterCommit() {
                // 当前事务提交完成后会执行下面的逻辑
                // 会使用当前方法的事务
                operateService.send(sendRecordEntity);
            }
        });
        log.info("测试结束");
    }

    /**
     * 发送消息
     * @param sendRecordEntity
     */
    private void send(SendRecordEntity sendRecordEntity) {
        log.info("当前事务名：{}", TransactionSynchronizationManager.getCurrentTransactionName());
        operateService.send(sendRecordEntity);
    }

    /**
     * 测试事务失效
     */
    @Override
    public void testTxInvalid() {
        log.info("测试开始");
        // 会导致add方法的事务失效
        add("zhangsan", new BigDecimal(100));
        // @EnableAspectJAutoProxy(exposeProxy = true)，需要将exposeProxy属性设为true
        TransferService service = (TransferService) AopContext.currentProxy();
        service.add("zhangsan", new BigDecimal(100));
        log.info("测试结束");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void catchException() {
        try{
            AccountEntity account = accountRepository.findByUsername("zhangsan");
            account.setBalance(new BigDecimal(10000));
            accountRepository.save(account);
            int i = 1/0;
        } catch (Exception e) {
            log.info("产生异常", e);
        }
    }

}
