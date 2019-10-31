//package com.fehead.coredata.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//
///**
// * @author lmwis
// * @description:
// * @date 2019-08-17 10:20
// * @Version 1.0
// */
//@Configuration
//public class SwaggerConfig {
//
//    @Bean
//    public Docket controllerApi() {
//
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(new ApiInfoBuilder()
//                .title("fehead patriotism controller API")
//                .version("4.2")
//                .build())
//            .select()
//            .apis(RequestHandlerSelectors.basePackage("com.fehead.coredata.controller"))
//            .paths(PathSelectors.any())
//            .build();
//    }
//}
