package com.wky.demo.event;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

/**
 *
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
