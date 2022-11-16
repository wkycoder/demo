package com.wky.demo.test;
import com.wky.demo.model.entity.UserEntity;

/**
 * @author wangkunyang
 * @date 2022/06/17 12:22
 */
public class TestWrapperClass {

    public static void main(String[] args) {

        Integer i = 0;
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1);
        userEntity.setPassword("ddd");
        userEntity.setDeleted(i);

        i = 1;

        System.out.println(userEntity);




    }

}
