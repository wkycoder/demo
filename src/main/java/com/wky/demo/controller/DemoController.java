package com.wky.demo.controller;

import com.alibaba.excel.EasyExcel;
import com.wky.demo.common.Result;
import com.wky.demo.exception.GlobalRuntimeException;
import com.wky.demo.juc.CompletableFutureService;
import com.wky.demo.listener.DemoListener;
import com.wky.demo.model.entity.UserEntity;
import com.wky.demo.model.enums.UrlSuffixEnum;
import com.wky.demo.model.vo.ExcelVO;
import com.wky.demo.service.DemoService;
import com.wky.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

/**
 * red packet  红包
 * 命名查询和自定义查询
 * @author: wangkunyang
 * @date 2021/08/27 09:30
 */
@Api(value = "接口列表", tags = {"接口列表"})
@RestController
@RequestMapping("/demo")
public class DemoController {

    @Resource
    private RestTemplate restTemplate;
    @Resource
    private DemoService demoService;
    @Resource
    private UserService userService;
    @Resource
    private CompletableFutureService completableFutureService;

    @ApiOperation(value = "导入Excel文件")
    @PostMapping("/import/excel")
    public Result<?> uploadExcel(String fileUrl) {
        // 校验url
        if (StringUtils.isEmpty(fileUrl) || checkSuffix(fileUrl)) {
            return Result.error("文件路径为空或不符合指定格式");
        }
        // 根据url去下载对应的excel文件
        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<byte[]> response = restTemplate.exchange(fileUrl, HttpMethod.GET, new HttpEntity<byte[]>(headers), byte[].class);
        if (response.getBody() == null) {
            return Result.error("未获取到数据");
        }
        byte[] result = response.getBody();
        // 转换成字节流
        InputStream inputStream = new ByteArrayInputStream(result);
        // 处理excel文件
        EasyExcel.read(inputStream, ExcelVO.class, new DemoListener(demoService)).doReadAll();
        return Result.ok("处理成功", null);
    }

    /**
     * 检查给定url的后缀是否符合系统的规定
     *
     * @param url
     * @return
     */
    private boolean checkSuffix(String url) {
        String[] split = StringUtils.split(url, ".");
        String suffix = split[split.length - 1];
        return UrlSuffixEnum.XLS.getValue().equals(suffix) || UrlSuffixEnum.XLSX.getValue().equals(suffix);
    }

    @ApiOperation(value = "删除用户信息")
    @GetMapping("/delete/{userId}")
    public Result<?> deleteUser(@PathVariable("userId") Integer userId) {
        userService.deleteUser(userId);
        return Result.ok("删除成功", null);
    }

    @ApiOperation(value = "查询用户信息")
    @GetMapping("/query/{userId}")
    public Result<?> queryUser(@PathVariable("userId") Integer userId) {
        UserEntity userEntity = userService.queryUser(userId);
        return Result.ok("查询成功", userEntity);
    }

    @SneakyThrows
    @ApiOperation(value = "抢红包")
    @GetMapping("/grab/{redId}")
    public Result<?> grabRed(@PathVariable("redId") Integer redId) {
        Integer amount = demoService.grabRed(redId);
        return Result.ok("成功", amount);
    }

    @ApiOperation(value = "测试requires_new")
    @GetMapping("/test/requiresNew")
    public Result<?> testRN() {
        demoService.testRN();
        return Result.ok("测试成功", null);
    }

    @ApiOperation(value = "手动捕获异常不抛出")
    @GetMapping("/test/notThrow")
    public Result<?> testNT() {
        demoService.testNT();
        return Result.ok("测试成功", null);
    }

    @ApiOperation(value = "一个事务方法调用同类的另一个事务方法")
    @GetMapping("/test/callOther")
    public Result<?> callOtherMethod() {
        demoService.methodA();
        return Result.ok("测试成功", null);
    }


    @ApiOperation(value = "test post")
    @PostMapping("/test/post")
    public Result<?> testPost(@RequestBody List<Integer> ids) {
        System.out.println(ids);
        return Result.ok("测试成功", null);
    }

    @ApiOperation(value = "pos机支付接口")
    @PostMapping("/pos/pay")
    public Result<Boolean> pay() {
        boolean payResult;
        try {
            payResult = completableFutureService.pay();
        } catch (Exception e) {
            throw new GlobalRuntimeException("支付失败");
        }
        return Result.ok("测试成功", payResult);
    }

}
