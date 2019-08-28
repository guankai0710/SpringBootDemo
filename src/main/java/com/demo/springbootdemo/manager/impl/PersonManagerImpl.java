package com.demo.springbootdemo.manager.impl;

import com.demo.springbootdemo.entity.Person;
import com.demo.springbootdemo.entity.PersonCriteria;
import com.demo.springbootdemo.manager.IPersonManager;
import com.demo.springbootdemo.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户业务操作接口
 *
 * @Author: guan.kai
 * @CreateTime: 2019/8/28 14:34
 * @Version: 1.0.0
 **/
@Component
@Transactional(rollbackFor = Exception.class)
public class PersonManagerImpl implements IPersonManager {

    @Autowired
    private IPersonService personService;

    /**
     * 根据用户id获取用户数据
     *
     * @Author: guan.kai
     * @CreateTime: 2019/8/28 14:36
     * @Param: [id]
     * @Return: com.demo.springbootdemo.entity.Person
     **/
    @Override
    public Person getById(String id) {
        return personService.getById(id);
    }

    /**
     * 查询所有用户数据
     *
     * @Author: guan.kai
     * @CreateTime: 2019/8/28 14:37
     * @Return: java.util.List<com.demo.springbootdemo.entity.Person>
     **/
    @Override
    public List<Person> selectAll() {
        PersonCriteria criteria = new PersonCriteria();
        return personService.selectByExample(criteria);
    }
}
