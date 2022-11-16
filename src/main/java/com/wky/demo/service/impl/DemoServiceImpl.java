package com.wky.demo.service.impl;

import com.wky.demo.model.entity.RedEntity;
import com.wky.demo.model.vo.ExcelVO;
import com.wky.demo.repository.RedRepository;
import com.wky.demo.service.DemoService;
import com.wky.demo.service.OperateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: wangkunyang
 * @date 2021/08/27 11:17
 */
@Slf4j
@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    private RedRepository redRepository;

    @Autowired
    private OperateService operateService;

    private Lock lock = new ReentrantLock();

    /**
     * 处理Excel中的数据
     * @param list
     */
    @Override
    public void processData(List<ExcelVO> list) {

    }

    /**
     * 抢红包
     * 先释放锁，后提交事务：
     * 这样会导致其他请求拿到旧的值，进而导致值未能达到预期。
     * 多次写操作的值一样
     * @param redId
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer grabRed(Integer redId) throws InterruptedException {
        // 查询红包的剩余金额
//        Integer remainingAmount = redRepository.findRemainingAmount(redId);
//        if (remainingAmount <= 0) {
//            return 0;
//        }
        // 创建分布式锁
        Integer randomAmount = 0;
        lock.lock();
        try {
            // 再次检查余额
            Integer remainingAmount = redRepository.findRemainingAmount(redId);
            log.info("余额：{}", remainingAmount);
            if (remainingAmount > 0) {
                // 生成一个随机金额
//                Integer randomAmount = RandomUtils.nextInt(0, redRepository.findTotalAmount(redId));
                 randomAmount = 10;
                if (randomAmount > remainingAmount) {
                    randomAmount = remainingAmount;
                }
                log.info("抢到的金额：{}", randomAmount);
                // 扣减金额
                redRepository.updateRemainingAmountById(remainingAmount - randomAmount, redId);
            }
        } catch (Exception e) {
            log.error("出现异常", e);
        } finally {
            lock.unlock();
        }
        // 事务提交前睡眠一段时间
        TimeUnit.MILLISECONDS.sleep(5000);
        // 释放分布式锁
        return randomAmount;
    }

    /**
     * 测试RN
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void testRN() {
        operateService.add("zhangsan", new BigDecimal(10));
        log.info("加钱成功");
    }

    /**
     * 测试手动捕获异常不抛出
     */
    @Override
    public void testNT() {
        log.info("开始测试---");
        try {
            operateService.add("zhangssan", new BigDecimal(100));
            int i = 1 / 0;
        } catch (Exception e) {
            // 把异常catch住什么都不做，这样就不会导致事务回滚
            log.error("测试出现异常", e);
        }
        log.info("测试结束---");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void methodA() {
        log.info("methodB transaction name：{}", TransactionSynchronizationManager.getCurrentTransactionName());
//        methodB();
        // A调用B
        ((DemoService) AopContext.currentProxy()).methodB();
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void methodB() {
        log.info("methodB is invoked...");
        log.info("methodB transaction name：{}", TransactionSynchronizationManager.getCurrentTransactionName());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void testCache() {
        RedEntity redEntity = redRepository.findById(1).orElse(null);
        redEntity.setRemainingAmount(100);

    }


}
