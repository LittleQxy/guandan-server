package com.guandan.guandanserver;


import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(
        scanBasePackages = {"com.guandan"})
@EnableAsync
public class GuandanServerApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(GuandanServerApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }

}
