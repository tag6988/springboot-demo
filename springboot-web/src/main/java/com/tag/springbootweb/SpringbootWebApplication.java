package com.tag.springbootweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;

/*exclude = {DataSourceAutoConfiguration.class},默认数据源*/
@SpringBootApplication(
        exclude = {DataSourceAutoConfiguration.class},
        scanBasePackages = {"com.tag.springbootmybatis", "com.tag" +
                ".springbootswagger", "com.tag.springbootweb", "com.tag" +
                ".springbootshiro", "com.tag.springbootjpa"})
@EnableAsync    //开启多线程支持使用@Async实现异步调用
public class SpringbootWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootWebApplication.class, args);
    }

}
