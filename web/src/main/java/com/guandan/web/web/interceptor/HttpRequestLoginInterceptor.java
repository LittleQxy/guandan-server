package com.school.practice.market.web.interceptor;

import com.school.practice.market.web.context.UserContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: qixiangyang.121
 * @Description:
 * @Date: 17:19 2021/1/4
 */
@Slf4j
public class HttpRequestLoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        Long loginUserId = UserContext.getLoginUserId();
        if (loginUserId == null) {
            loginUserId = 0L;
        }
        log.info("Begin to handle HTTP request, employee_id: {}, path: {}, params: {}", loginUserId,
                request.getServletPath(), request.getParameterMap());
        return true;
    }
}
