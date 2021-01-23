package com.guandan.core;


import com.guandan.core.network.websocket.GameWebSocketSession;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


/**
 * @Author: qixiangyang.121
 * @Description:
 * @Date: 18:34 2020/1/1
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferData implements Serializable {
    private GameWebSocketSession gameWebSocketSession;
    private String channel;
    private int protocol;
    private byte[] data;
}
