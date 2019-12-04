//package com.demo.springbootdemo.config;
//
//import com.rabbitmq.client.Connection;
//import com.rabbitmq.client.ConnectionFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.io.IOException;
//import java.util.concurrent.TimeoutException;
//
///**
// * 消息队列连接配置
// *
// * @author: guan.kai
// * @date: 2019/12/3 9:21
// **/
//@Configuration
//public class MQConnectionConfig {
//
//    @Bean(name = "rabbitMqConnection")
//    public Connection getConnection() {
//        //设置工厂
//        ConnectionFactory factory = new ConnectionFactory();
//        factory.setHost("192.168.1.235");
//        factory.setPort(5672);
//        //设置vhost
//        factory.setVirtualHost("/tzb");
//        factory.setUsername("test");
//        factory.setPassword("123456");
//        //通过工厂获取连接
//        Connection connection = null;
//        try {
//            connection = factory.newConnection();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (TimeoutException e) {
//            e.printStackTrace();
//        }
//        return connection;
//    }
//}
