package com.wky.demo.repository;

import com.wky.demo.model.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: wangkunyang
 * @date 2021/08/30 17:49
 */
@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Integer> {

    /**
     * 根据用户名查询账户信息
     * @param username
     * @return
     */
    AccountEntity findByUsername(String username);
}
