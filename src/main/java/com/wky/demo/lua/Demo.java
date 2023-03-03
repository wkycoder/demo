package com.wky.demo.lua;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * @author wuming
 * @date 2023/2/12/02/12 20:56
 */
public class Demo {

    @Resource
    private RedisTemplate  redisTemplate;

    public void test() {
        String script = "if redis.call('get', KEYS[1]) == 1 return 0";
        RedisScript<Long> redisScript = RedisScript.of(script, Long.class);
        redisTemplate.execute(redisScript, Arrays.asList("test"));


    }

    public static void main(String[] args) {









    }

}
