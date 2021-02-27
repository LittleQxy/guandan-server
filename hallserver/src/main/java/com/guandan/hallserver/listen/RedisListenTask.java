package com.guandan.hallserver.listen;

import com.guandan.core.TransferData;
import com.guandan.hallserver.GameServer;
import com.guandan.hallserver.config.HallConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;



/**
 * @Author: qixiangyang.121
 * @Description:
 * @Date: 18:34 2020/1/1
 */
@Component
@Slf4j
public class RedisListenTask {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private HallConfig hallConfig;


    @Async
    public void doMessageTask(Message message, byte[] bytes) {
        //1.获取消息
        TransferData transferData = (TransferData) this.redisTemplate.getDefaultSerializer().deserialize(message.getBody());
        if (transferData.getData() != null) {
            log.info(new String(transferData.getData()));

            GameServer.send(Integer.valueOf(hallConfig.getReverseRoutes().get(transferData.getChannel())),
                    Integer.valueOf(transferData.getProtocol()),
                    transferData.getGameWebSocketSession().getSessionId(),
                    transferData.getData());
        }
    }
}


