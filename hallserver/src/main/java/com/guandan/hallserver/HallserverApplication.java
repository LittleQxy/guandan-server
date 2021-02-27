package com.guandan.hallserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(
        scanBasePackages = {"com.guandan"}
)
public class HallserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(HallserverApplication.class, args);
    }

}
