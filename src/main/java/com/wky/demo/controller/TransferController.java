package com.wky.demo.controller;

import com.wky.demo.common.Result;
import com.wky.demo.model.req.TransferReq;
import com.wky.demo.service.TransferService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation(value = "测试Spring事务扩展机制", notes = "测试Spring事务扩展机制")
    @GetMapping("/committed/process")
    public Result test() {
        transferService.testCommitted();
        return Result.ok("测试成功", null);
    }

    @ApiOperation(value = "测试Spring事务失效", notes = "事务方法被同类中的其他方法调用")
    @GetMapping("/transaction/invalid")
    public Result testTxInvalid() {
        transferService.testTxInvalid();
        return Result.ok("测试成功", null);
    }


    @ApiOperation(value = "测试在事务中抛出异常并捕获", notes = "测试在事务中抛出异常并捕获")
    @GetMapping("/catch/exception")
    public Result catchException() {
        transferService.catchException();
        return Result.ok("测试成功", null);
    }




}
