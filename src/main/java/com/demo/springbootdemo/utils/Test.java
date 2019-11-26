package com.demo.springbootdemo.utils;

import com.demo.springbootdemo.thread.TestThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.io.IOException;

/**
 * 测试类
 *
 * @author: guan.kai
 * @date: 2019/11/19 14:26
 **/
public class Test {

    @Autowired
    private static ThreadPoolTaskExecutor threadPoolTaskExecutor;

    public static void main(String[] args) throws IOException {

//        threadPoolTaskExecutor.execute(new TestThread("hhhhh"));

    }

}
