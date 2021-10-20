package com.wky.demo.service;

/**
 * @author: wangkunyang
 * @date 2021/09/30 14:33
 */
public interface BotService {

    /**
     * 使用机器人向企业微信发送消息
     * @param msg
     */
    void sendMsg(String msg);

}
