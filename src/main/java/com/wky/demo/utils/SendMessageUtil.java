package com.wky.demo.utils;

import com.google.common.net.MediaType;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: wangkunyang
 * @date 2021/09/30 17:50
 */
@Data
@Slf4j
public class SendMessageUtil {

    private static final String ROBOT_KEY = "";

    public Boolean sendMessage(String url, String message) {
//        QiRobotVo vo = new QiRobotVo();
//        //机器人地址
//        vo.setWebhookAddress(url);
//        //1.第一种情况：发送文本消息
//        vo.setContent(message);
//        vo.setMsgType("text");
        String reqBody = "";
//        reqBody = "{\n" +
//                "\t\"msgtype\": \"" + vo.getMsgType() + "\",\n" +
//                "    \"text\": {\n" +
//                "        \"content\": \"" + vo.getContent() + "\"" +
//                "    }\n" +
//                "}";

//        OkHttpClient client = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)// 设置连接超时时间
//                .readTimeout(20, TimeUnit.SECONDS)// 设置读取超时时间
//                .build();
        MediaType contentType = MediaType.parse("application/json; charset=utf-8");
//        RequestBody body = RequestBody.create(contentType, reqBody);
//        Request request = new Request.Builder().url(url).post(body).addHeader("cache-control", "no-cache").build();
//        Response response = null;
        byte[] datas = null;
//        try {
//            response = client.newCall(request).execute();
//            datas = response.body().bytes();
//        } catch (IOException e) {
//            log.error("发送告警信息失败：{}", e.getMessage());
//            return false;
//        }
        String respMsg = new String(datas);
        log.info("发送告警信息：{}", respMsg);
        return true;
    }



    /**
     * 企业微信机器人发送消息
     * @param planEntity
     * @return
     */
//    private void weChatRobotSendAlert(PurchasePlanEntity planEntity,String extraMsg){
//        Map map=new HashMap(2);
//        //设置数据格式为text
//        map.put("msgtype","text");
//        //存放text内容
//        Map text=new HashMap(2);
//        //设置content
//        StringBuilder content=new StringBuilder();
//        content.append("采购计划异常，请相关同事注意。\n批次号:"+planEntity.getPurchasePlanPatch()+"\n采购计划主键id:"+planEntity.getId());
//        if(!StringUtils.isEmpty(extraMsg)){
//            content.append("\n其他信息：\n"+extraMsg);
//        }
//        //设置提醒人
//        List<String> mentionedList=alertList;
//        text.put("content",content);
//        text.put("mentioned_list",mentionedList);
//
//        map.put("text",text);
//        HttpHeaders headers=new HttpHeaders();
//        MediaType mediaType = MediaType.parseMediaType("application/json;charset=UTF-8");
//        headers.setContentType(mediaType);
//        //组装参数
//        String s = JSON.toJSONString(map);
//        HttpEntity<Map<String,Object>> entity=new HttpEntity<>(map,headers);
//
//        restTemplate.postForObject(weChatRobotUrl, entity, String.class);
//    }

}
