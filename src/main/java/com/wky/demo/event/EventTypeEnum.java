package com.wky.demo.event;

/**
 * @author lijiajia
 * @date 2021/6/23 20:08
 */
public enum EventTypeEnum {


    /**
     * 用户信息发生了变更.发送事件
     * 1.部门变更
     * 2.角色变更
     * 3.领导部门变更
     */
    USER_INFO_CHANGE,
    /**
     * 但凡有用户新增,发送加入用户事件,一个用户仅一次加入事件
     */
    USER_ADD,
    /**
     * 但凡有用户退出,发送用户退出事件,一个用户仅一次退出事件
     */
    USER_QUIT,
    /**
     * 用户禁用事件
     */
    USER_DISABLE,
    /**
     * 用户启用事件
     */
    USER_ENABLE,

    /**
     * 用户预离职
     */
    USER_PRE_RESIGN,

    /**
     * 用户角色发生变更
     */
    USER_ROLE_CHANGE,
    /**
     * 角色权限发生变更
     */
    ROLE_PERMISSION_CHANGE,


    /**
     * 拜访小结发生变更
     */
    VISIT_SUMMARY_CHANGE,
    /**
     * 释放关联的主单数据
     */
    RELEASE_MAIN_ORDER,

}
