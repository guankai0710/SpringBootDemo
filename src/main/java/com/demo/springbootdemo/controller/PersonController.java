package com.demo.springbootdemo.controller;

import com.alibaba.fastjson.JSON;
import com.demo.springbootdemo.entity.Person;
import com.demo.springbootdemo.enumeration.SexEnum;
import com.demo.springbootdemo.manager.IPersonManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 用户操作接口
 *
 * @Author: guan.kai
 * @CreateTime: 2019/8/14 22:15
 * @Version: 0.0.1
 **/
@Controller
@RequestMapping("/person")
public class PersonController {

    @Resource
    private IPersonManager personManager;

    @RequestMapping("/show")
    @ResponseBody
    public String show(){
        Person person = personManager.getById("337617ab5fb54206abc1e908784e6d95");
        if (person != null){
            person.setSexDoc(SexEnum.getByValue(person.getSex()).getDoc());
        }
        return JSON.toJSONString(person);
    }

}
