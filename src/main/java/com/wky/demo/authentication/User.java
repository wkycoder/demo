package com.wky.demo.authentication;

import lombok.Data;

import java.util.Set;

/**
 * @author wuming
 * @date 2024/5/18/05/18 20:17
 */
@Data
public class User {

    private Long userId;

    private String userName;

    private String nickName;

    private Set<String > roleCodes;

}
