package com.demo.springbootdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 起始页
 *
 * @author: guan.kai
 * @date: 2019/8/29 21:50
 **/
@Controller
public class MainController {

    /**
     * 登录页面
     *
     * @return
     */
    @GetMapping("/login")
    @ResponseBody
    public String login(){
        return "请登录";
    }
}
