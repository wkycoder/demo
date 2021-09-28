package com.wky.demo.service;

import com.wky.demo.model.entity.SendRecordEntity;

/**
 * @author: wangkunyang
 * @date 2021/09/17 10:13
 */
public interface SendRecordService {

    /**
     * 异步更新消息发送记录
     * @param sendRecordEntity
     */
    void updateSendRecord(SendRecordEntity sendRecordEntity);
}
