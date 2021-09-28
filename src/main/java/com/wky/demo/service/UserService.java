package com.wky.demo.service;

import com.wky.demo.model.entity.UserEntity;

/**
 * @author: wangkunyang
 * @date 2021/08/31 16:38
 */
public interface UserService {

    /**
     * 删除用户信息
     * @param userId
     */
    void deleteUser(Integer userId);

    /**
     * 查询用户信息
     * @param userId
     * @return
     */
    UserEntity queryUser(Integer userId);
}
