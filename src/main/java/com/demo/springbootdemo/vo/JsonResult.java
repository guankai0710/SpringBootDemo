package com.demo.springbootdemo.vo;

import java.io.Serializable;

/**
 * 自定义控制器返回模型
 *
 * @author: guan.kai
 * @date: 2019/8/19 22:04
 **/
public class JsonResult implements Serializable {

    private static final long serialVersionUID = 1320619120873828769L;

    /**
     * 状态
     **/
    boolean success;

    /**
     * 信息
     **/
    String msg;

    /**
     * 数据
     **/
    transient Object obj;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
