package com.frame.handler.exception;


import com.frame.enums.AppHttpCodeEnum;

public class SystemException extends RuntimeException {
    private final int code;
    private final String message;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return message;
    }

    public SystemException(AppHttpCodeEnum httpCodeEnum) {
        super(httpCodeEnum.getMsg());
        this.code = httpCodeEnum.getCode();
        this.message = httpCodeEnum.getMsg();
    }
}
