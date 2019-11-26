package com.demo.springbootdemo.thread;

import java.util.concurrent.Callable;

/**
 * TODO
 *
 * @author: guan.kai
 * @date: 2019/11/25 9:45
 **/
public class TestThread implements Callable {

    private String name;

    public TestThread() {
    }

    public TestThread(String name) {
        this.name = name;
    }


    @Override
    public String call() throws Exception {
        return name+"ceshi";
    }
}
