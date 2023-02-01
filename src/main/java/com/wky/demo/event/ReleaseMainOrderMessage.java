package com.wky.demo.event;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author: cg1
 * @date: 2022-01-05 18:41
 **/
@Data
@AllArgsConstructor
public class ReleaseMainOrderMessage {

    /**
     * 门店ID
     */
    Integer storeId;

    /**
     * 主单号
     */
    String mainOrderNumber;

    /**
     * 流水发生时间
     */
    LocalDateTime trxTime;

}
