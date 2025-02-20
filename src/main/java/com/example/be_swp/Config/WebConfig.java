//package com.example.be_swp.Config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//    @Override
//    public void addCorsMappings(CorsRegistry corsRegistry){
//        corsRegistry
//                .addMapping("/api/**")
//                .allowedOrigins(
//                    "http://3.26.7.116:3000/",
//                    "http://localhost:5173",
//                    "http://localhost:3000",
//                    "http://35.202.71.223:8080/"
//                )
//                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE")
//                .allowedHeaders("Authorization", "Content-Type")
//                .allowCredentials(true);
//
//        corsRegistry
//                .addMapping("/swagger-ui/**")
//                .allowedOrigins(
//                    "http://3.26.7.116:3000/",
//                    "http://localhost:5173",
//                    "http://localhost:3000",
//                    "http://35.202.71.223:8080/"
//                )
//                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE")
//                .allowedHeaders("Authorization", "Content-Type")
//                .allowCredentials(true);
//
//        corsRegistry
//                .addMapping("/v3/api-docs/**")
//                .allowedOrigins(
//                    "http://3.26.7.116:3000/",
//                    "http://localhost:5173",
//                    "http://localhost:3000",
//                    "http://35.202.71.223:8080/"
//                )
//                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE")
//                .allowedHeaders("Authorization", "Content-Type")
//                .allowCredentials(true);
//    }
//}
