package com.wky.demo.controller;

import com.wky.demo.common.Result;
import com.wky.demo.service.DemoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: wangkunyang
 * @date 2022/03/09 15:03
 */
@Api(value = "接口列表", tags = {"接口列表"})
@RestController
@RequestMapping("/jpa")
public class JpaController {

    @Resource
    private DemoService demoService;

    @ApiOperation(value = "导入Excel文件")
    @PostMapping("/import/excel")
    public Result uploadExcel(String fileUrl) {

        return Result.ok("处理成功", null);
    }

    @ApiOperation(value = "测试hibernate")
    @PostMapping("/cache")
    public Result testCache() {
        demoService.testCache();
        return Result.ok("处理成功", null);
    }



}
