package com.school.practice.market.exception;

/**
 * @Author: qixiangyang.121
 * @Description:
 * @Date: 16:06 2020/12/22
 */
public class BusinessException extends RuntimeException {

    private final int code;

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(int code, String message, Throwable throwable) {
        super(message, throwable);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
