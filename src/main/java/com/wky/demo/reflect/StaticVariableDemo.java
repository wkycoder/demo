package com.wky.demo.reflect;

import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

/**
 * 利用反射设置静态变量的值
 *
 * @author wuming
 * @date 2024/5/18/05/18 20:15
 */
public class StaticVariableDemo {


    public static void main(String[] args) {

        Field field = ReflectionUtils.findField(TestUtil.class, "name");
        assert field != null;
        ReflectionUtils.makeAccessible(field);
        ReflectionUtils.setField(field, null, "wuming");
        System.out.println(TestUtil.getName());


    }






}
