package com.wky.demo.repository;

import com.wky.demo.model.entity.OrderEntity;
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
public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {

    /**
     * 更新deleted字段的值
     * @param deleted
     * @param userId
     */
    @Modifying(clearAutomatically = true)
    @Transactional(rollbackFor = Exception.class)
    @Query("update OrderEntity o set o.deleted = ?1 where o.userId = ?2")
    void updateDeletedByUserId(Integer deleted, Integer userId);
}
