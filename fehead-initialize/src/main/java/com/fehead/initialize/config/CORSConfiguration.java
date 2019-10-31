package com.fehead.initialize.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author lmwis
 * @description:
 * @date 2019-08-25 20:26
 * @Version 1.0
 */
@Configuration
public class CORSConfiguration {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                //addMapping 跨域所能访问的路径
                //allowedOrigins：那些域可以访问，默认为任何域都可以访问
                registry.addMapping("**").allowedOrigins("*");
            }
        };
    }
}
