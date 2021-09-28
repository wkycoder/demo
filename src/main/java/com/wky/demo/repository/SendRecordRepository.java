package com.wky.demo.repository;

import com.wky.demo.model.entity.SendRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: wangkunyang
 * @date 2021/08/30 17:49
 */
@Repository
public interface SendRecordRepository extends JpaRepository<SendRecordEntity, Integer> {


}
