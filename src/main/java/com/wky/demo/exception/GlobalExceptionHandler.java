package com.wky.demo.exception;

import com.wky.demo.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author: wangkunyang
 * @date 2021/08/31 09:01
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler({GlobalRuntimeException.class})
    public Result<?> handleException(GlobalRuntimeException e) {
        log.error("发生异常：{}", e.getMsg());
        return Result.error(e.getCode(), e.getMsg());
    }

}
