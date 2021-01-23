package com.school.practice.market.service.impl;

import com.school.practice.market.service.SessionService;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Author: qixiangyang.121
 * @Description:
 * @Date: 20:32 2020/12/22
 */
@Service
public class SessionServiceImpl implements SessionService {

    private static final String KEY_PREFIX = "MarkSession+";

    @Resource
    private RedisTemplate<String, String> redisTemplate;


    @Override
    public String getUserToken(String key){
        if (StringUtils.isNotBlank(key)){
            String token = redisTemplate.opsForValue().get(buildKey(key));
            return token;
        }else {
            return null;
        }
    }

    @Override
    public String setUserToken(String token){
        if(StringUtils.isNotBlank(token)){
            String uuid = UUID.randomUUID().toString();
            redisTemplate.opsForValue().set(buildKey(uuid),token,2, TimeUnit.HOURS);
            return uuid;
        }
        return null;
    }


    private String buildKey(String key){
        return KEY_PREFIX+key;
    }

}
