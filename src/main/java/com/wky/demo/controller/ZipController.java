package com.wky.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * red packet  红包
 * 命名查询和自定义查询
 * @author: wangkunyang
 * @date 2021/08/27 09:30
 */
@Api(value = "接口列表", tags = {"接口列表"})
@RestController
@RequestMapping("/export")
public class ZipController {

//    @Autowired
//    private ZipService zipService;


    @ApiOperation(value = "批量下载图片并导出为zip")
    @PostMapping("/zip")
    public void exportZip() {


    }

}
