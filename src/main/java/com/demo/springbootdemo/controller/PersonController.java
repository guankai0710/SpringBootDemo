package com.demo.springbootdemo.controller;

import com.alibaba.fastjson.JSON;
import com.demo.springbootdemo.entity.Person;
import com.demo.springbootdemo.enumeration.SexEnum;
import com.demo.springbootdemo.exceptions.MyOwnRuntimeException;
import com.demo.springbootdemo.manager.IPersonManager;
import com.demo.springbootdemo.utils.MyCollectionsUitl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

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
    private static final Logger logger = LoggerFactory.getLogger(PersonController.class);

    @Resource
    private IPersonManager personManager;

    @RequestMapping("/show")
    @ResponseBody
    public String show(){
        Person person = personManager.getById("337617ab5fb54206abc1e908784e6d95");
        if (person != null){
            try {
                person.setSexDoc(SexEnum.getByValue(person.getSex()).getDoc());
            } catch (MyOwnRuntimeException e) {
                logger.error(e.getMessage());
            }
        }
        return JSON.toJSONString(person);
    }

    @RequestMapping("/all")
    @ResponseBody
    public String all(){
        List<Person> personList = personManager.selectAll();
        List<List<Person>> subList = MyCollectionsUitl.subList(personList, 2);
        String result = JSON.toJSONString(subList);
        return result;
    }

}
