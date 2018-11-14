package com.tag.springbootweb;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(/*exclude = {DataSourceAutoConfiguration.class}, */scanBasePackages = {"com.tag.springbootmybatis", "com.tag.springbootswagger", "com.tag.springbootweb", "com.tag.springbootshiro"})
//@MapperScan("com.tag.springbootmybatis.mapper")
public class SpringbootWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootWebApplication.class, args);
    }
}
