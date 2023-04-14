package com.wky.demo.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import javax.annotation.Resource;

/**
 * 释放交易流水监听器
 *
 **/
@Component
@Slf4j
public class ReleaseMainOrderListener {

    @Resource
//    private VisitService visitService;

    /**
     * 释放关联的主单
     *
     * @param event
     */
    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT, condition = "#event.type.equals(T(com.ruigu.crm.rp.framework.event.EventTypeEnum).RELEASE_MAIN_ORDER)")
    public void release(EventMessage<ReleaseMainOrderMessage> event) {
        // 释放主单
        ReleaseMainOrderMessage message = event.getMessage();
    }

}
