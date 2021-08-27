package com.wky.demo.controller;

import com.alibaba.excel.EasyExcel;
import com.wky.demo.common.Result;
import com.wky.demo.listener.DemoListener;
import com.wky.demo.model.enums.UrlSuffixEnum;
import com.wky.demo.model.vo.ExcelVO;
import com.wky.demo.service.DemoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * @author: wangkunyang
 * @date 2021/08/27 09:30
 */
@Api(value = "接口列表")
@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DemoService demoService;

    @ApiOperation(value = "导入Excel文件")
    @PostMapping("/import/excel")
    public Result uploadExcel(String fileUrl) {
        // 校验url
        if (StringUtils.isEmpty(fileUrl) || checkSuffix(fileUrl)) {
            return Result.error("文件路径为空或不符合指定格式");
        }
        // 根据url去下载对应的excel文件
        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<byte[]> response = restTemplate.exchange(fileUrl, HttpMethod.GET, new HttpEntity<byte[]>(headers), byte[].class);
        byte[] result = response.getBody();
        // 转换成字节流
        InputStream inputStream = new ByteArrayInputStream(result);
        // 处理excel文件
        EasyExcel.read(inputStream, ExcelVO.class, new DemoListener(demoService)).doReadAll();
        return Result.ok("处理成功", null);
    }

    /**
     * 检查给定url的后缀是否符合系统的规定
     * @param url
     * @return
     */
    private boolean checkSuffix(String url) {
        String[] split = StringUtils.split(url, ".");
        String suffix = split[split.length - 1];
        if (UrlSuffixEnum.XLS.getValue().equals(suffix) || UrlSuffixEnum.XLSX.getValue().equals(suffix)) {
            return true;
        }
        return false;
    }

}
