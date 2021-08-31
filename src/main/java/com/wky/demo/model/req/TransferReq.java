package com.wky.demo.model.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author: wangkunyang
 * @date 2021/08/30 16:48
 */
@Data
public class TransferReq {

    @ApiModelProperty(name = "from", value = "操作用户")
    private String from;

    @ApiModelProperty(name = "to", value = "目标用户")
    private String to;

    @ApiModelProperty(name = "amount", value = "转账金额")
    private BigDecimal amount;



}
