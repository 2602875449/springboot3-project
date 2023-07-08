package com.frame.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.frame.enums.AppHttpCodeEnum;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseResult<T> implements Serializable {
    private Integer code;
    private String msg;
    private T data;

    public ResponseResult() {
        this(AppHttpCodeEnum.SUCCESS);
    }

    public ResponseResult(Integer code, T data) {
        this(code, AppHttpCodeEnum.SUCCESS.getMsg(), data);
    }

    public ResponseResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResponseResult(Integer code, String msg) {
        this(code, msg, null);
    }

    public ResponseResult(AppHttpCodeEnum enums) {
        this(enums.getCode(), enums.getMsg(), null);
    }

    public static <T> ResponseResult<T> errorResult(int code, String msg) {
        return new ResponseResult<>(code, msg);
    }

    public static <T> ResponseResult<T> okResult() {
        return new ResponseResult<>();
    }

    public static <T> ResponseResult<T> okResult(int code, String msg) {
        return new ResponseResult<>(code, msg);
    }

    public static <T> ResponseResult<T> okResult(T data) {
        return new ResponseResult<>(AppHttpCodeEnum.SUCCESS.getCode(), AppHttpCodeEnum.SUCCESS.getMsg(), data);
    }

    public static <T> ResponseResult<T> errorResult(AppHttpCodeEnum enums) {
        return new ResponseResult<>(enums);
    }

    public static <T> ResponseResult<T> errorResult(AppHttpCodeEnum enums, String msg) {
        return new ResponseResult<>(enums.getCode(), msg);
    }

    public ResponseResult<T> error(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
        return this;
    }

    public ResponseResult<T> ok(Integer code, T data) {
        this.code = code;
        this.data = data;
        return this;
    }

    public ResponseResult<T> ok(Integer code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
        return this;
    }

    public ResponseResult<T> ok(T data) {
        this.data = data;
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
