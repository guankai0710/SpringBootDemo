package com.demo.springbootdemo.service;

import com.demo.springbootdemo.entity.Person;
import com.demo.springbootdemo.entity.PersonCriteria;

import java.util.List;

/**
 * 用户操作接口
 *
 * @author: guan.kai
 * @date: 2019/8/14 22:17
 **/
public interface IPersonService {

    /**
     * 根据用户id获取用户数据
     *
     * @param id 用户id
     * @return 用户信息
     */
    Person getById(Integer id);

    /**
     * 根据条件查询用户数据
     *
     * @param criteria 用户信息查询条件
     * @return 用户信息集合
     */
    List<Person> selectByExample(PersonCriteria criteria);

}
