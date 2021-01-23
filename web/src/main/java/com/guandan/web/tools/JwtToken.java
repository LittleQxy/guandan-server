package com.school.practice.market.tools;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.school.practice.market.web.request.UserInfoRequest;

/**
 * @Author: qixiangyang.121
 * @Description:
 * @Date: 15:15 2020/12/22
 */
public class JwtToken {

    public static String getToken(UserInfoRequest request){
        String token = "";
        token = JWT.create().withAudience(String.valueOf(request.getEmployeeId()))
                .sign(Algorithm.HMAC256(request.getPassword()));
        return token;
    }



}
