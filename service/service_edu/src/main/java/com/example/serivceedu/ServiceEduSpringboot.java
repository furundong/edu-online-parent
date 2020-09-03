package com.example.serivceedu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * create by Freedom on 2020/8/13
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.example.servicebase"
        , "com.example.serivceedu"
})
public class ServiceEduSpringboot {

    public static void main(String[] args) {
        SpringApplication.run(ServiceEduSpringboot.class, args);
    }

}
