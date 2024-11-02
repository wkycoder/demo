package com.wky.demo.interview.java;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 为什么重写equals方法就必须重写hashCode方法？
 *
 * @author wuming
 * @date 2024/3/14/03/14 10:34
 */
public class EqualsAndHashCodeDemo {


    public static void main(String[] args) {

        Person a = new Person("xiaoming", 18);
        Person b = new Person("xiaoming", 18);
        Map<Person, Integer> map = new HashMap<>();
        map.put(a, 10);
        map.put(b, 20);
        // 假设1：只重写equals，不重写hashCode
        map.forEach((key, value) -> {
            System.out.println(key + ":" + value);
        });
        System.out.println(map.get(a));
        System.out.println(map.get(b));

    }

    static class Person {

        String name;
        Integer age;

        public Person(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

//        @Override
//        public boolean equals(Object o) {
//            if (this == o) return true;
//            if (o == null || getClass() != o.getClass()) return false;
//            Person person = (Person) o;
//            return Objects.equals(name, person.name) && Objects.equals(age, person.age);
//        }

        @Override
        public int hashCode() {
            return Objects.hash(name, age);
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }







}
