package com.wky.demo.redis;


import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * https://redis.io/docs/reference/protocol-spec/
 * 手写一个Redis的java客户端
 * RESP: Redis Serialization Protocol, 是一个基于TCP的应用层协议。
 *
 * @author wuming
 * @date 2023/3/17/03/17 21:50
 */
public class RedisClient {

    private final InputStream reader;
    private final OutputStream writer;

    public RedisClient() {
        try {
            // 连接上本机的Redis
            Socket client = new Socket("192.168.135.130", 6379);
            // 读取目标服务器响应的数据
            reader = client.getInputStream();
            // 外目标服务器写数据
            writer = client.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("创建连接失败");
        }
    }

    public String set(String key, String value) {
        // * 表示是数组，说明当前Redis命令有几个部分
        String command = "*3" + "\r\n" +
                // $ 表示是字符串，并说明字符串的长度
                "$3" + "\r\n" +
                "SET" + "\r\n" +
                "$" + key.length() + "\r\n" +
                key + "\r\n" +
                "$" + value.length() + "\r\n" +
                value + "\r\n";
        // 执行SET命令
        return execute(command);
    }

    public String get(String key) {
        String command = "*2" + "\r\n" +
                "$3" + "\r\n" +
                "GET" + "\r\n" +
                "$" + key.length() + "\r\n" +
                key + "\r\n";
        return execute(command);
    }

    private String execute(String command) {
        try {
            writer.write(command.getBytes());
            byte[] response = new byte[1024];
            // 获取Redis服务器的响应结果
            int result = reader.read(response);
            return parseResponse(response);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("执行命令失败");
        }
    }

    private String parseResponse(byte[] response) {
        String[] split = StringUtils.split(new String(response), "\r\n");
        if (split != null) {
            String result = split[0];
            if (result.startsWith("$") && split.length >= 2) {
                result = split[1];
            }
            return result;
        }
        throw new RuntimeException("服务器响应数据格式异常，无法解析");
    }

    public void exit() {
        try {
            writer.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
