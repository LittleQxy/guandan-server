package com.school.practice.market.exception;

import com.school.practice.market.enums.ErrorCodeEnum;
import com.school.practice.market.web.response.SingleResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: qixiangyang.121
 * @Description:
 * @Date: 17:02 2020/12/22
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理请求参数不合法
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public SingleResponse MethodArgNotValidException(HttpServletRequest request,
                                                     MethodArgumentNotValidException e){
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        StringBuilder stringBuilder = new StringBuilder();
        errors.forEach(error -> {
            stringBuilder.append(error.getDefaultMessage());
            stringBuilder.append(" ");
        });
        String errorMsg = stringBuilder.toString();
        log.warn("request param not valid, msg = "+errorMsg);
        return SingleResponse.buildFailedRes(ErrorCodeEnum.PARAM_INVALID.getCode(),errorMsg);
    }

    /**
     * 处理自定义业务异常
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public SingleResponse bizExceptionHandler(HttpServletRequest request,BusinessException e){
        log.warn("request error, msg = "+e.getMessage());
        return SingleResponse.buildFailedRes(e.getCode(),e.getMessage());
    }

    /**
     * 处理其他的异常
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public SingleResponse ExceptionHandler(HttpServletRequest request,Exception e){
        log.warn("request error, msg = "+e.getMessage());
        return SingleResponse.buildFailedRes(ErrorCodeEnum.SYSTEM_ERROR.getCode(), e.getMessage());
    }

}
