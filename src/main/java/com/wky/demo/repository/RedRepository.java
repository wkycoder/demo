package com.wky.demo.repository;

import com.wky.demo.model.entity.RedEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: wangkunyang
 * @date 2021/08/30 17:49
 */
@Repository
public interface RedRepository extends JpaRepository<RedEntity, Integer> {


    /**
     * 查询剩余的金额
     * @param redId
     * @return
     */
    @Query("select r.remainingAmount from RedEntity r where r.id = ?1")
    Integer findRemainingAmount(Integer redId);

    /**
     * 查询总金额
     * @param redId
     * @return
     */
    @Query("select r.totalAmount from RedEntity r where r.id = ?1")
    Integer findTotalAmount(Integer redId);

    /**
     * 更新红包余额
     * @param remainingAmount
     * @param id
     */
    @Modifying
    @Transactional(rollbackFor = Exception.class)
    @Query("update RedEntity r set r.remainingAmount = ?1 where r.id = ?2")
    void updateRemainingAmountById(Integer remainingAmount, Integer id);
}
