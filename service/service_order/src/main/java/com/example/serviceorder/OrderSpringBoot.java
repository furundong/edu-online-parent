package com.example.serviceorder;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * create by Freedom on 2020/11/20
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.example")
@MapperScan("com.example.serviceorder.dao")
@EnableDiscoveryClient
@EnableFeignClients
public class OrderSpringBoot {
    public static void main(String[] args) {
        SpringApplication.run(OrderSpringBoot.class, args);
    }
}
