package com.school.practice.market.web.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.school.practice.market.enums.ErrorCodeEnum;
import com.school.practice.market.exception.BusinessException;
import com.school.practice.market.exception.NotLoginException;
import com.school.practice.market.mybatis.mapper.po.EmployeePO;
import com.school.practice.market.mybatis.mapper.po.UserInfoPO;
import com.school.practice.market.service.EmployeeService;
import com.school.practice.market.service.SessionService;
import com.school.practice.market.service.UserInfoService;
import com.school.practice.market.web.conf.PassToken;
import com.school.practice.market.web.conf.UserLoginToken;
import com.school.practice.market.web.context.UserContext;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @Author: qixiangyang.121
 * @Description:
 * @Date: 15:25 2020/12/22
 */
public class AuthcationInterceptor implements HandlerInterceptor{


    @Autowired
    SessionService sessionService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String sessionId = request.getHeader("market-sessionId");
        String token = sessionService.getUserToken(sessionId);
        //不是映射到方法直接返回
        if (!(handler instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod)handler;
        Method method = handlerMethod.getMethod();

        //是否越过token验证
        if(method.isAnnotationPresent(PassToken.class)){
            PassToken passToken = method.getAnnotation(PassToken.class);
            if(passToken.required()){
                return true;
            }
        }
        if (method.isAnnotationPresent(UserLoginToken.class)){
            UserLoginToken loginToken = method.getAnnotation(UserLoginToken.class);
            //执行认证
            if(loginToken.required()){
                if(!StringUtils.isNotBlank(token)){
                    throw new NotLoginException("token已过期或者不存在，请重新登录");
                }
                String userId;
                try{
                    userId = JWT.decode(token).getAudience().get(0);
                }catch (JWTDecodeException e){
                    throw new BusinessException(ErrorCodeEnum.SYSTEM_ERROR.getCode(),"token解码异常，请重新登录");
                }
                if(!StringUtils.isNotBlank(userId)){
                    throw new NotLoginException();
                }
                EmployeePO employeePO = employeeService.selectById(Long.valueOf(userId));
                UserInfoPO userInfoPO = userInfoService.selectByEmployeeId(Long.valueOf(userId));
                if (userInfoPO == null){
                    throw new NotLoginException("当前用户不存在");
                }
                //验证token
                JWTVerifier verifier = JWT.require(Algorithm.HMAC256(userInfoPO.getPassword())).build();
                try{
                    verifier.verify(token);
                }catch (JWTVerificationException e){
                    throw new NotLoginException("当前token无效，请重新登录");
                }
                UserContext.setLoginUserId(employeePO.getId());
                if(!UserContext.isLonginState()){
                    throw new NotLoginException("设置员工ID失效");
                }
                return true;
            }
        }
       return true;
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        UserContext.clear();
    }


}
