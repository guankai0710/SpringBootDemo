package com.demo.springbootdemo.service;

import com.demo.springbootdemo.entity.Person;
import com.demo.springbootdemo.entity.PersonCriteria;

import java.util.List;

/**
 * 用户操作接口
 *
 * @Author: guan.kai
 * @CreateTime: 2019/8/14 22:17
 * @Version: 0.0.1
 **/
public interface IPersonService {

    Person getById(String id);

    List<Person> selectByExample(PersonCriteria criteria);

}
