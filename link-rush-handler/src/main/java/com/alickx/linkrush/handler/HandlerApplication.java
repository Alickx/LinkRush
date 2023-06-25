package com.alickx.linkrush.handler;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.alickx.linkrush")
@MapperScan("com.alickx.linkrush.handler.mapper")
public class HandlerApplication {
    public static void main(String[] args) {
        SpringApplication.run(HandlerApplication.class, args);
    }
}
