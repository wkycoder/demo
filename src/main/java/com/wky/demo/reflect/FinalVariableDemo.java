package com.wky.demo.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * 利用反射设置常量的值，按照普通变量的方式设置，会报错
 *
 * Could not access method or field: Can not set static final...
 *
 * @author wuming
 * @date 2024/5/19/05/19 09:04
 */
public class FinalVariableDemo {


    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException {
//        Field field = ReflectionUtils.findField(TestUtil.class, "address");
//        assert field != null;
//        field.setAccessible(true);
//        field.set(null, "def");
//        ReflectionUtils.setField(field, null, "def");

        System.out.println(TestUtil.getAddress());

        // 如果一定要设置，利用modifiers字段去除常量字段的final修饰符
        // 但是jdk8以后无效，因为内部对modifiers字段的访问做了限制
        Field field = TestUtil.class.getDeclaredField("address");
        field.setAccessible(true);
        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        // 修改address字段的修饰符，去掉final修饰符
        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
        field.set(null, "newValue");

        // 无法更改编译期常量，只能通过反射修改运行期常量
        // private static final String address = "abc";
        System.out.println(TestUtil.getAddress());


    }


}
