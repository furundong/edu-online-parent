package com.example.serviceoss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * create by Freedom on 2020/8/18
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class) //父项目service已经用了MySQL，所有你这里需要写
@ComponentScan({"com.example.servicebase"
        ,"com.example.serviceoss"
    })
public class OssApplication {

    public static void main(String[] args) {
        SpringApplication.run(OssApplication.class, args);
    }

}
