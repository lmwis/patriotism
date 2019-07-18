package com.fehead;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@MapperScan("com.fehead.initialize.dao")
public class InitializeApplication
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        SpringApplication.run(InitializeApplication.class, args);
    }
}
