package com.example.servicecms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * create by Freedom on 2020/9/24
 */
@SpringBootApplication
@ComponentScan({"com.example"}) //指定扫描位置
@MapperScan("com.example.servicecms.dao")
public class CmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(CmsApplication.class, args);
    }
}
