package com.wky.demo.redis;

/**
 * @author wuming
 * @date 2023/3/18/03/18 13:06
 */
public class RedisClientTest {

    public static void main(String[] args) {

        RedisClient redisClient = new RedisClient();

        String res = redisClient.set("test", "1");
        System.out.println(res);

        String value = redisClient.get("test");
        System.out.println(value);

        redisClient.exit();

    }


}
