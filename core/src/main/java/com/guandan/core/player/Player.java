package com.guandan.core.player;

import com.guandan.core.network.websocket.GameWebSocketSession;
import lombok.Data;


/**
 * @Author: qixiangyang.121
 * @Description:
 * @Date: 10:48 2020/1/07
 */
@Data
public class Player {
    private GameWebSocketSession gameWebSocketSession;
    public int chip;  //得分
    private boolean isReady; //是否就绪
    private String roomId;  //房间号
    private boolean isDisConnection;  //断开连接
    private boolean isDisbanded;

    private boolean isOp;

    public Player(int chip, boolean isReady, GameWebSocketSession gameWebSocketSession) {
        this.chip = chip;
        this.isReady = isReady;
        this.gameWebSocketSession = gameWebSocketSession;
    }
}
