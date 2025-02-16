package com.example.be_swp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.example.be_swp.Repository")  // Thay bằng package chứa repository của bạn
public class SWPApplication {
    public static void main(String[] args) {
        SpringApplication.run(SWPApplication.class, args);
    }
}
