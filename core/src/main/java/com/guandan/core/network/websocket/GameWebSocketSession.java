package com.guandan.core.network.websocket;


import io.netty.channel.ChannelId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.io.Serializable;


/**
 * @Author: qixiangyang.121
 * @Description:
 * @Date: 18:56 2020/1/17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameWebSocketSession implements Serializable {
    @Id
    private String id;
    private ChannelId sessionId;
    private String token;
    private String lastLoginTime;
    private String lastLogoutTime;
    //0: 登陆 1:退出
    private String state;
    private String address;
    private String channel;
    private String roomNumber;
}
