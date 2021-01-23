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
    public int chip;
    private boolean isReady;
    private String roomId;
    private boolean isDisConnection;
    private boolean isDisbanded;

    private boolean isOp;

    public Player(int chip, boolean isReady, GameWebSocketSession gameWebSocketSession) {
        this.chip = chip;
        this.isReady = isReady;
        this.gameWebSocketSession = gameWebSocketSession;
    }
}
