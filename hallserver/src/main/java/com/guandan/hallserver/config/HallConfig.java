package com.guandan.hallserver.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;


/**
 * @Author: qixiangyang.121
 * @Description:
 * @Date: 18:34 2020/1/1
 */
@Component
@Data
@ConfigurationProperties(prefix = "hall")
public class HallConfig {

    private Map<String, String> routes = new HashMap<>(); //接收prop1里面的属性值
    private Map<String, String> reverseRoutes = new HashMap<>();

    private String name;


    @PostConstruct
    public void init() {
        /**
         * 扩展游戏，大厅服务器，将多个游戏进行映射，根据channel——id获取对应的游戏
         *     1: hallserver
         *     2: guandan
         */
        for (Map.Entry<String, String> entry : routes.entrySet()) {
            reverseRoutes.put(entry.getValue(), entry.getKey());
        }
    }
}
