package com.guandan.core.room;



/**
 * @Author: qixiangyang.121
 * @Description:
 * @Date: 10:34 2020/1/12
 */
public interface RoomAction<T,D> {
    /**
     * 房间消息处理类
     * @param message 消息
     * @param context 当前房间上下文
     * @return
     */
    void roomAction(T message, D context);
}
