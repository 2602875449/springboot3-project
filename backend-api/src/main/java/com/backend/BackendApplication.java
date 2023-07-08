package com.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.backend", "com.frame"})
public class BackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class);
    }
}
