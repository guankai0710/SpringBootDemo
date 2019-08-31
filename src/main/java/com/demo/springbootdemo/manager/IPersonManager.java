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
     * 根据用户账号获取用户数据
     *
     * @param account 账号
     * @return
     */
    Person getByAccount(String account);

    /**
     * 查询所有用户数据
     *
     * @return 用户信息集合
     */
    List<Person> selectAll();

    /**
     * 注册账号
     *
     * @param account 账号
     * @param password 密码
     */
    void registerAccount(String account, String password);
}
