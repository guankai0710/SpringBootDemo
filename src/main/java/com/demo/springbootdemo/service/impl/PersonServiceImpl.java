package com.demo.springbootdemo.service.impl;

import com.demo.springbootdemo.entity.Person;
import com.demo.springbootdemo.entity.PersonCriteria;
import com.demo.springbootdemo.mapper.PersonMapper;
import com.demo.springbootdemo.service.IPersonService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Transactional
public class PersonServiceImpl implements IPersonService {

    @Resource
    private PersonMapper personMapper;

    @Override
    public Person getById(String id) {
        return personMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Person> selectByExample(PersonCriteria criteria) {
        return personMapper.selectByExample(criteria);
    }
}
