package com.example.be_swp.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${project.cors.allowed-origins}")
    private String[] allowedOrigins;

    @Value("${project.cors.allowed-methods}")
    private String[] allowedMethods;

    @Override
    public void addCorsMappings(CorsRegistry corsRegistry){
        corsRegistry.addMapping("/api/**").allowedOrigins(allowedOrigins).allowedMethods(allowedMethods);
    }

}
