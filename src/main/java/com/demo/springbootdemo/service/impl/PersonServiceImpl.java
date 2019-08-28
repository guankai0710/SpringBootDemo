package com.demo.springbootdemo.service.impl;

import com.demo.springbootdemo.entity.Person;
import com.demo.springbootdemo.entity.PersonCriteria;
import com.demo.springbootdemo.dao.PersonMapper;
import com.demo.springbootdemo.service.IPersonService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户操作接口
 *
 * @Author: guan.kai
 * @CreateTime: 2019/8/14 22:21
 * @Version: 0.0.1
 **/
@Service
public class PersonServiceImpl implements IPersonService {

    @Resource
    private PersonMapper personMapper;

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
        return personMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据条件查询用户数据
     *
     * @Author: guan.kai
     * @CreateTime: 2019/8/28 14:37
     * @Param: [criteria]
     * @Return: java.util.List<com.demo.springbootdemo.entity.Person>
     **/
    @Override
    public List<Person> selectByExample(PersonCriteria criteria) {
        return personMapper.selectByExample(criteria);
    }
}
