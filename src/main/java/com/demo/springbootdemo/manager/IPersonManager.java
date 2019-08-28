package com.demo.springbootdemo.manager;

import com.demo.springbootdemo.entity.Person;

import java.util.List;

/**
 * 用户业务操作接口
 *
 * @Author: guan.kai
 * @CreateTime: 2019/8/28 14:33
 * @Version: 1.0.0
 **/
public interface IPersonManager {

    /**
     * 根据用户id获取用户数据
     *
     * @Author: guan.kai
     * @CreateTime: 2019/8/28 14:36
     * @oaram: [id：用户id]
     * @Return: com.demo.springbootdemo.entity.Person
     **/
    Person getById(String id);

    /**
     * 查询所有用户数据
     *
     * @Author: guan.kai
     * @CreateTime: 2019/8/28 14:37
     * @Return: java.util.List<com.demo.springbootdemo.entity.Person>
     **/
    List<Person> selectAll();
}
