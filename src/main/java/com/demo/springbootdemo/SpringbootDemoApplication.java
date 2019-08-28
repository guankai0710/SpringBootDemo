package com.demo.springbootdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * spring boot项目启动入口
 *
 * @Author: kai.guan
 * @CreateTime: 2019/8/10 0:53
 **/
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.demo.springbootdemo.dao") //扫描的mybatis接口
public class SpringbootDemoApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDemoApplication.class, args);
    }

    /**
     * 为了打包springboot项目
     *
     * @Author: kai.guan
     * @CreateTime: 2019/8/14 22:14
     * @Param: [builder]
     * @return: org.springframework.boot.builder.SpringApplicationBuilder
     **/
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
    }
}
