package com.guandan.hallserver.listen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Component;


/**
 * @Author: qixiangyang.121
 * @Description:
 * @Date: 18:34 2020/1/1
 */
@Component
public class RedisListen extends MessageListenerAdapter {


    @Autowired
    private RedisListenTask redisListenTask;


    @Override
    public void onMessage(Message message, byte[] bytes) {
        redisListenTask.doMessageTask(message,bytes);
    }
}
