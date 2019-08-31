package com.demo.springbootdemo.exceptions;

/**
 * 自定义请求超时异常
 *
 * @author: guan.kai
 * @date: 2019/8/28 15:11
 **/
public class MyOwnRuntimeException extends Exception {

    public MyOwnRuntimeException(){
        super();
    }

    public MyOwnRuntimeException(String msg){
        super(msg);
    }

}
