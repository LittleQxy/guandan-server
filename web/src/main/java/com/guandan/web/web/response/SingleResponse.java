package com.school.practice.market.web.response;

import com.school.practice.market.enums.ErrorCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: qixiangyang.121
 * @Description:
 * @Date: 11:48 2020/12/22
 */
@ApiModel("统一返回体")
@Data
public class SingleResponse<T> {

    @ApiModelProperty(value = "返回内容")
    private T data;

    private Integer code;
    private String message;

    public static<T>  SingleResponse<T> buildSuccessRes(T data){
        SingleResponse<T> response = new SingleResponse<>();
        response.setCode(ErrorCodeEnum.SUCCESS.getCode());
        response.setData(data);
        response.setMessage(ErrorCodeEnum.SUCCESS.getDesc());
        return response;
    }

    public static<T>  SingleResponse<T> buildSuccessRes(){
        SingleResponse<T> response = new SingleResponse<>();
        response.setCode(ErrorCodeEnum.SUCCESS.getCode());
        response.setMessage(ErrorCodeEnum.SUCCESS.getDesc());
        return response;
    }

    public static<T> SingleResponse<T> buildFailedRes(Integer code, String msg){
        SingleResponse<T> response = new SingleResponse<>();
        response.setCode(code);
        response.setMessage(msg);
        return response;
    }
}
