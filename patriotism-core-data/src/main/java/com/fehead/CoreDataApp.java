package com.fehead;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */

@SpringBootApplication
public class CoreDataApp {
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        SpringApplication.run(CoreDataApp.class,args);


    }
}
