package com.demo.springbootdemo.controller;

import com.demo.springbootdemo.entity.Person;
import com.demo.springbootdemo.manager.IPersonManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.annotation.Resource;

/**
 * 登录、注册
 *
 * @author: guan.kai
 * @date: 2019/8/30 9:13
 **/
@Controller
@SessionAttributes("user")
public class LoginController {

    @Resource
    private IPersonManager personManager;

    /**
     * 登录页面
     *
     * @return
     */
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    /**
     * 登录操作
     *
     * @param model
     * @param account 账号
     * @param password 密码
     * @return
     */
    @PostMapping("/login")
    public String login(Model model, String account, String password){
        Person person = new Person();
        model.addAttribute("user",person);
        return "";
    }
}
