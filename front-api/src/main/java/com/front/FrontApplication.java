package com.front;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.front", "com.frame"})
public class FrontApplication {
    public static void main(String[] args) {
        SpringApplication.run(FrontApplication.class);
    }
}
