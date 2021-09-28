package com.wky.demo.service.impl;

import com.wky.demo.model.entity.UserEntity;
import com.wky.demo.repository.OrderRepository;
import com.wky.demo.repository.UserRepository;
import com.wky.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.Savepoint;

/**
 * @author: wangkunyang
 * @date 2021/08/31 16:38
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    /**
     * 删除用户信息
     * 前提：该方法存在事务
     * 当我们使用jpa的save/delete等更新方法时，接着又调用了@Query自定义查询的方法时需要注意缓存（不要轻易清空缓存）
     * 在同一个事务中，更新一个对象（记录），接着直接查询该对象，那么查出来的对象还是没更新前的状态。
     * @Modifying(clearAutomatically = true) ：清缓存的同时会把未提交的修改给扔掉。
     * @param userId
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteUser(Integer userId) {
        // 先查一次（先放到缓存中一份）
        UserEntity first = userRepository.findById(userId).orElseThrow(null);
        // 删除用户(逻辑删除)，直接操作数据库而非缓存
        userRepository.updateDeletedById(1, userId);
        // 再查一次
        UserEntity second = userRepository.findById(userId).orElseThrow(null);
        System.out.println("删除成功");
    }



    public void deleteUser2(Integer userId) throws SQLException {
        // 删除用户及该用户对应的所有订单信息（逻辑删除）
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(null);
        userEntity.setDeleted(1);
        userRepository.save(userEntity);
        // 我们通过设置Savepoint(保存点)回滚到了事务中某个特殊的点, 而不是回滚整个事务.
        Savepoint savepoint = dataSource.getConnection().setSavepoint("test");
        orderRepository.updateDeletedByUserId(1, userId);
        // 模拟事务回滚
        int i = 1/0;
    }

    /**
     * 查询用户信息
     * @param userId
     * @return
     */
    @Override
    public UserEntity queryUser(Integer userId) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(null);
        return userEntity;
    }

}
