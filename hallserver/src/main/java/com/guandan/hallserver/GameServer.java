package com.guandan.hallserver;

import com.guandan.core.TransferData;
import com.guandan.core.action.DispatcherAction;
import com.guandan.core.network.websocket.GameWebSocket;
import com.guandan.core.network.websocket.GameWebSocketSession;
import com.guandan.hallserver.config.HallConfig;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yeauty.annotation.ServerEndpoint;


/**
 * @Author: qixiangyang.121
 * @Description:
 * @Date: 18:34 2020/1/1
 */
@Component
/**
 * netty-websocket:
 *   host: localhost
 *   port: 8083
 *   path: /hall
 */
@Slf4j
@ServerEndpoint(prefix = "netty-websocket")
public class GameServer extends GameWebSocket {


    @Autowired
    private DispatcherAction dispatcherAction;


    @Autowired
    private HallConfig hallConfig;

    @Override
    protected boolean receiveHandle(GameWebSocketSession session, int channel, int protocol, byte[] buffer) {
        String channelString = hallConfig.getRoutes().get(String.valueOf(channel));
        if (channelString.equals(hallConfig.getName())) {
            dispatcherAction.createAction(protocol);
        } else {
            this.redisTemplate.convertAndSend(channelString, new TransferData(session, channelString, protocol, buffer));
        }
        return true;
    }

    @Override
    protected void openHandle(GameWebSocketSession session) {

        if(session.getChannel() != null && !session.getChannel().isEmpty())
        {
            this.redisTemplate.convertAndSend(session.getChannel(), new TransferData(session, "", 1001, null));
        }
    }

    @Override
    protected void closeHandle(GameWebSocketSession session) {
        if(session.getChannel() != null && !session.getChannel().isEmpty()){
            this.redisTemplate.convertAndSend(session.getChannel(), new TransferData(session, "", 1002, null));
        }
    }
}
