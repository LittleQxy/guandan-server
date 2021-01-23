package com.school.practice.market.web.conf;

import com.school.practice.market.web.interceptor.AuthcationInterceptor;
import com.school.practice.market.web.interceptor.HttpRequestLoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: qixiangyang.121
 * @Description:
 * @Date: 16:19 2020/12/22
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authcationInterceptor())
                .addPathPatterns("/**");
        registry.addInterceptor(httpRequestLoginInterceptor())
                .addPathPatterns("/**");
    }

    @Bean
    public AuthcationInterceptor authcationInterceptor(){
        return new AuthcationInterceptor();
    }

    @Bean
    public HttpRequestLoginInterceptor httpRequestLoginInterceptor(){
            return new HttpRequestLoginInterceptor();
    }

}
