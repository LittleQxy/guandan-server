package com.school.practice.market.service;

/**
 * @Author: qixiangyang.121
 * @Description:
 * @Date: 20:32 2020/12/22
 */
public interface SessionService {

    /**
     * 根据sessionid获取token
     * @param key
     * @return
     */
    public String getUserToken(String key);

    /**
     * 设置token的过期时间为20分钟
     * @param token
     * @return
     */
    public String setUserToken(String token);


}
