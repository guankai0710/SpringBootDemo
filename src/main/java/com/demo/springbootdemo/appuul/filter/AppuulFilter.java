package com.demo.springbootdemo.appuul.filter;

/**
 * 描述：TODO
 *
 * @author guankai
 * @date 2023/4/24
 **/
public abstract class AppuulFilter {

    abstract public String filterType();
    abstract public int filterOrder();
    abstract public void run() throws Exception;

}
