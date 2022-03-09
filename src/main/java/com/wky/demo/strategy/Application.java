package com.wky.demo.strategy;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author: wangkunyang
 * @date 2021/11/14 12:43
 */
public class Application {

    public static void main(String[] args) throws IOException {
        Properties props = new Properties();
        props.load(new FileInputStream("./config.properties"));
        String type = (String) props.get("eviction.type");
        EvictionStrategy evictionStrategy = EvictionStrategyFactory.getEvictionStrategy(type);
        // ...
        // 非运行时动态确定-->编译时静态确定
        evictionStrategy = new LruEvictionStrategy();

    }

}
