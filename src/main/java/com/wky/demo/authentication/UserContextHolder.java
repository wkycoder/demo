package com.wky.demo.authentication;

/**
 * @author wuming
 * @date 2024/5/18/05/18 20:17
 */
public class UserContextHolder {

    private static final ThreadLocal<User> userContextHolder = new InheritableThreadLocal<>();

    public static void setUser(User user) {
        userContextHolder.set(user);
    }

    public static User getUser() {
        return userContextHolder.get();
    }

    public static void clear() {
        userContextHolder.remove();
    }

}
