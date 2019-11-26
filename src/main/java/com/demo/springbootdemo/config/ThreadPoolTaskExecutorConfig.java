package com.demo.springbootdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池配置
 *
 * @author: guan.kai
 * @date: 2019/11/25 9:40
 **/
@Configuration
@EnableAsync
public class ThreadPoolTaskExecutorConfig {

    @Bean(name = "threadPoolTaskExecutor")
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
        pool.setKeepAliveSeconds(300);
        //核心线程池数
        pool.setCorePoolSize(50);
        //最大线程
        pool.setMaxPoolSize(100);
        //队列容量
        pool.setQueueCapacity(1000);
        //队列满，线程被拒绝执行策略
        pool.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return pool;
    }
}
