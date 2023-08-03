package com.my.auth;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * @program: oa-parent
 * @description:
 * @author: DY
 * @create: 2023-07-29 20:13
 **/
@SpringBootApplication
//@MapperScan(basePackages = "com.my.auth.mapper")
@ComponentScan("com.my")
public class ServiceAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceAuthApplication.class, args);
    }
}
