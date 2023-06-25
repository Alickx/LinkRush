package com.alickx.linkrush.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.alickx.linkrush"})
public class ApiApplication {
    public static void main(String[] args) {

        SpringApplication.run(ApiApplication.class, args);
    }

}
