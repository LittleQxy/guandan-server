package com.school.practice.market.exception;

import com.school.practice.market.enums.ErrorCodeEnum;

/**
 * @Author: qixiangyang.121
 * @Description:
 * @Date: 15:59 2020/12/22
 */
public class NotLoginException extends BusinessException {


    public NotLoginException() {
        super(ErrorCodeEnum.NOT_LOGIN.getCode(), ErrorCodeEnum.NOT_LOGIN.getDesc());
    }

    public NotLoginException(String message) {
        super(ErrorCodeEnum.NOT_LOGIN.getCode(), message);
    }

    public NotLoginException(int code, String message) {
        super(code, message);
    }

    public NotLoginException(int code, String message, Throwable cause) {
        super(code, message, cause);
    }
}
