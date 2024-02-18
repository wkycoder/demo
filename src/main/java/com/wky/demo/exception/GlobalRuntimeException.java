package com.wky.demo.exception;

import lombok.Data;

/**
 * @author: wangkunyang
 * @date 2021/08/31 08:59
 */
@Data
public class GlobalRuntimeException extends RuntimeException {

    private final Integer code;
    private final String msg;

    public GlobalRuntimeException(int code, String message) {
        super(message);
        this.code = code;
        this.msg = message;
    }

    public GlobalRuntimeException(String message) {
        this(500, message);
    }


}
