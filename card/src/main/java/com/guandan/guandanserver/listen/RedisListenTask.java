package com.guan.card.listen;

import com.guandan.core.TransferData;
import com.guandan.core.action.DispatcherAction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;

@Component
@Slf4j
public class RedisListenTask {



    @Autowired
    private RedisTemplate redisTemplate;


    @Autowired
    private DispatcherAction dispatcherAction;

    @Async
    public void doMessageTask(Message message, byte[] bytes){

        TransferData transferData = (TransferData) Objects.requireNonNull(this.redisTemplate.getDefaultSerializer()).deserialize(message.getBody());

        dispatcherAction.createAction(transferData.getProtocol()).ifPresent(p-> {
            try {
                log.info("run action :" + transferData.getProtocol());
                p.requestAction(transferData);
            } catch (IOException e) {
                log.error("action error",e);
            }
        });
    }
}
