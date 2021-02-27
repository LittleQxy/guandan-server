package com.guandan.hallserver.config;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.yeauty.standard.ServerEndpointExporter;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;


/**
 * @Author: qixiangyang.121
 * @Description:
 * @Date: 18:34 2020/1/1
 */
@Configuration
@Data
public class WebSocketConfig {

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

}
