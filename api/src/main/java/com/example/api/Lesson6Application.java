package com.example.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication(scanBasePackages = {"com.example.api", "com.example.core"})
@EnableConfigurationProperties
public class Lesson6Application {
    public static void main(String[] args) {
        SpringApplication.run(Lesson6Application.class, args);
    }
}
