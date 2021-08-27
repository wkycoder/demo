package com.wky.demo.common;

import lombok.Data;

/**
 * 统一返回结果
 * @author User
 * @date
 */
@Data
public class Result<T> {

    private Integer code;
    private String msg;
    private T data;

    private Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> Result<T> ok(T data) {
        return new Result<T>(200, "", data);
    }

    public static <T> Result<T> ok(String msg, T data) {
        return new Result<T>(200, msg, data);
    }

    public static <T> Result<T> error(String msg) {
        return new Result<T>(500, msg, null);
    }

    public static <T> Result<T> error(Integer code, String msg) {
        return new Result<T>(code, msg, null);
    }

}
