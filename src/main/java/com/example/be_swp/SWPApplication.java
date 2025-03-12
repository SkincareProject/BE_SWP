package com.example.be_swp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import java.util.TimeZone;

@SpringBootApplication
@EnableJpaRepositories("com.example.be_swp.Repository")  
public class SWPApplication {
    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Bangkok"));
        
        SpringApplication.run(SWPApplication.class, args);
    }
}
