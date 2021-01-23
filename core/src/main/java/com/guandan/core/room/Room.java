package com.guandan.core.room;

import akka.actor.AbstractActor;
import com.guandan.core.annotation.Protocol;
import com.guandan.core.room.message.baseMessage.RoomMessage;

/**
 * @Author: qixiangyang.121
 * @Description:
 * @Date: 17:12 2020/1/12
 */
public class Room extends AbstractActor {

    private RoomContext roomContext;

    Room(RoomContext roomContext) {
        this.roomContext = roomContext;
    }


    @Override
    public Receive createReceive() {
        return receiveBuilder().match(RoomMessage.class, message -> {
            Integer protocol = message.getClass().getAnnotation(Protocol.class).value();
            roomContext.getRoomManager().getCacheMap().get(protocol).roomAction(message, roomContext);
        }).build();
    }
}
