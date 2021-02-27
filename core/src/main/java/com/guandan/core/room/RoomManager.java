package com.guandan.core.room;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import com.guandan.core.annotation.Protocol;
import lombok.Data;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @Author: qixiangyang.121
 * @Description:
 * @Date: 19:13 2020/1/15
 */
@Data
@Component
/**
 * 基础的房间管理类，主要是用于创建游戏房间之后，房间内状态的流转
 */
public abstract class RoomManager {
    protected ConcurrentHashMap<String, ActorRef> map = new ConcurrentHashMap<>();
    Map<Integer, RoomAction> cacheMap = new HashMap<>();


    protected String connectorName;
    protected String serverName;
    protected RedisTemplate redisTemplate;
    protected ActorSystem actorSystem;
    protected ApplicationContext context;


    public RoomManager( RedisTemplate redisTemplate, ActorSystem actorSystem, ApplicationContext context) {
        this.redisTemplate = redisTemplate;
        this.actorSystem = actorSystem;
        this.context = context;
    }

    public ActorRef getRoomActorRef(String roomId){
        return map.get(roomId);
    }

    public void clearRoom(String roomId){
        ActorRef actorRef =  map.remove(roomId);
        actorSystem.stop(actorRef);
    }


    @PostConstruct
    public void init() {
        for (String bean : context.getBeanNamesForAnnotation(Protocol.class)) {
            Object o = context.getBean(bean);
            Protocol protocol = o.getClass().getAnnotation(Protocol.class);

            if(o.getClass().getSuperclass().equals(RoomAction.class)) {
                cacheMap.put(protocol.value(), (RoomAction) o);
            }
        }
    }
}
