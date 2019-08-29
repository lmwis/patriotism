package com.fehead.coredata.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/**
 * @author lmwis
 * @description:
 * @date 2019-08-17 18:51
 * @Version 1.0
 */
@Configuration
public class WebConfig {


    @Bean
    public Filter corsFilter(){
        return new CORSFilter();
    }


}