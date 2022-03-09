package com.wky.demo.repository;

import com.wky.demo.model.entity.UserEntity;
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
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    /**
     * 逻辑删除
     * @param deleted
     * @param userId
     */
    @Modifying
    @Transactional(rollbackFor = Exception.class)
    @Query("update UserEntity u set u.deleted = ?1 where u.id = ?2")
    void updateDeletedById(Integer deleted, Integer userId);
}
