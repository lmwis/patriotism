package com.fehead;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Hello world!
 *
 */

@EnableSwagger2
@SpringBootApplication
@MapperScan("com.fehead.coredata.dao")
public class CoreDataApp {
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        SpringApplication.run(CoreDataApp.class,args);


    }
}
