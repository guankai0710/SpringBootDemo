package com.demo.springbootdemo.jobs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 早起问候定时任务
 *
 * @Author: guan.kai
 * @CreateTime: 2019/8/28 11:32
 * @Version: 1.0.0
 **/
@EnableScheduling
public class HelloJob {
    private final Logger logger = LoggerFactory.getLogger(HelloJob.class);

    /**
     * 每天早上8点跑
     *
     * @Author: guan.kai
     * @CreateTime: 2019/8/28 11:38
     * @Param: []
     * @Return: void
     **/
    @Scheduled(cron = "0 0 8 * * ?")
    public void scheduled(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = format.format(new Date());
        logger.debug("定时任务：debug日志输出---"+date);
        logger.info("定时任务：info日志输出---"+date);
        logger.warn("定时任务：warn日志输出---"+date);
        logger.error("定时任务：error日志输出---"+date);
    }
}
