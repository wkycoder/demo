package com.wky.demo.service.impl;

import com.wky.demo.model.entity.SendRecordEntity;
import com.wky.demo.repository.SendRecordRepository;
import com.wky.demo.service.SendRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author: wangkunyang
 * @date 2021/09/17 10:13
 */
@Slf4j
@Service
public class SendRecordServiceImpl implements SendRecordService {

    @Autowired
    private SendRecordRepository sendRecordRepository;

    @Async
    @Override
    public void updateSendRecord(SendRecordEntity sendRecordEntity) {
        log.info("发送记录信息：{}", sendRecordEntity.toString());
        sendRecordRepository.save(sendRecordEntity);
    }

}
