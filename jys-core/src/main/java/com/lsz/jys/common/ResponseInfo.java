package com.lsz.jys.common;

import java.io.Serializable;

public class ResponseInfo<T> implements Serializable {
    public static final String CODE_SUCCESS = "0000";
    public static final String CODE_ERROR = "9999";
    public static final String CODE_ERROR_BUSINESS = "7777";

    public static final String CODE_SUCCESS_MSG = "成功";
    public static final String CODE_ERROR_MSG = "系统异常，请稍后再试";
    private static final long serialVersionUID = 0x20170713;
    private String code;

    private String message;

    private T data;

    public ResponseInfo(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ResponseInfo<T> success(T data) {
        return new ResponseInfo<>(CODE_SUCCESS, CODE_SUCCESS_MSG, data);
    }

    public static <T> ResponseInfo<T> success(String msg, T data) {
        return new ResponseInfo<>(CODE_SUCCESS, msg, data);
    }

    public static <T> ResponseInfo<T> assertion(T data) {
        if (data != null) {
            return success(data);
        } else {
            return error(data);
        }
    }

    public static <T> ResponseInfo<T> error(T data) {
        return new ResponseInfo<>(CODE_ERROR, CODE_ERROR_MSG, data);
    }


    public static <T> ResponseInfo<T> error(String code, String msg, T data) {
        return new ResponseInfo<>(code, msg, data);
    }

    public boolean isSuccess() {
        return CODE_SUCCESS.equals(code);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
