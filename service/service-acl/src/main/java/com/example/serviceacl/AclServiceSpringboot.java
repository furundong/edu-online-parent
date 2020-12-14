package com.example.serviceacl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * create by Freedom on 2020/11/30
 */

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan("com.example")
@MapperScan("com.example.serviceacl.dao")
public class AclServiceSpringboot {

    public static void main(String[] args) {
        SpringApplication.run(AclServiceSpringboot.class, args);
    }

}
