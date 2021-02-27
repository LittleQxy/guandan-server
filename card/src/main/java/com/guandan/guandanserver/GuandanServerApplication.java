package com.guan.card;


import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(scanBasePackages = {"com.guandan"})
@EnableAsync
public class GuandanApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(GuandanApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }

}
