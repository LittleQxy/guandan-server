package com.school.practice.market.enums;

import com.school.practice.market.enums.base.IIntegerEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: qixiangyang.121
 * @Description:
 * @Date: 11:51 2020/12/22
 */
@AllArgsConstructor
@Getter
public enum ErrorCodeEnum implements IIntegerEnum {

    NOT_LOGIN(-100,"未登录"),
    NOT_EXIST(-101,"员工不存在"),
    PARAM_INVALID(-102,"请求参数不合法"),
    REPEAT_REGISTER(-103,"信息已存在，重复注册"),
    SYSTEM_ERROR(-200,"系统异常"),
    SUCCESS(200,"成功请求");

    private Integer code;
    private String desc;
}
