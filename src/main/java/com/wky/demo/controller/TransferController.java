package com.wky.demo.controller;

import com.wky.demo.common.Result;
import com.wky.demo.model.req.TransferReq;
import com.wky.demo.service.TransferService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 转账
 * @author: wangkunyang
 * @date 2021/08/30 16:43
 */
@Api(value = "接口列表", tags = {"接口列表"})
@RestController
@RequestMapping("/demo")
public class TransferController {

    @Autowired
    private TransferService transferService;

    @ApiOperation(value = "转账", notes = "转账")
    @PostMapping("/transfer")
    public Result transfer(@RequestBody TransferReq transferReq) {
        // 转账操作：from-amount，to+amount
        transferService.transfer(transferReq);
        return Result.ok("转账成功", null);
    }

}
