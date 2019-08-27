package com.demo.springbootdemo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 测试类
 *
 * @Author: guan.kai
 * @CreateTime: 2019/8/10 0:54
 * @Version: 0.0.1
 **/
@Controller
@EnableScheduling
@RequestMapping("demo")
public class TestController {

    private final Logger logger = LoggerFactory.getLogger(TestController.class);

    /**
     * 测试接口
     *
     * @Author: kai.guan
     * @CreateTime: 2019/8/10 0:55
     * @return: java.lang.String
     **/
    @RequestMapping("test")
    @ResponseBody
    public String test(){
        return "测试接口！";
    }

    /**
     *
     *
     * @Author: kai.guan
     * @CreateTime: 2019/8/10 1:07
     * @return: void
     **/
//    @Scheduled(cron = "0/10 * * * * ?")
//    public void scheduled(){
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String date = format.format(new Date());
//        logger.debug("定时任务：debug日志输出---"+date);
//        logger.info("定时任务：info日志输出---"+date);
//        logger.warn("定时任务：warn日志输出---"+date);
//        logger.error("定时任务：error日志输出---"+date);
//    }
}
