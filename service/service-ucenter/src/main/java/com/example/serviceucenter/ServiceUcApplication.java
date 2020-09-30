package com.example.serviceucenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * create by Freedom on 2020/9/25
 */
@ComponentScan({"com.example"})
@SpringBootApplication//取消数据源自动配置
@MapperScan("com.example.serviceucenter.dao")
public class ServiceUcApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceUcApplication.class, args);
    }
}
