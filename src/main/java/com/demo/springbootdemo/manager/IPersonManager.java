package com.demo.springbootdemo.manager;

import com.demo.springbootdemo.entity.Person;

import java.util.List;

/**
 * 用户业务操作接口
 *
 * @author: guan.kai
 * @date: 2019/8/28 14:33
 **/
public interface IPersonManager {

    /**
     * 根据用户id获取用户数据
     *
     * @param id 用户id
     * @return 用户信息
     */
    Person getById(Integer id);

    /**
     * 查询所有用户数据
     *
     * @return 用户信息集合
     */
    List<Person> selectAll();
}
