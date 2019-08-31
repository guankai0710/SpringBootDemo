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
 * @author: guan.kai
 * @date: 2019/8/14 22:21
 **/
@Service
public class PersonServiceImpl implements IPersonService {

    @Resource
    private PersonMapper personMapper;

    /**
     * 根据用户id获取用户数据
     *
     * @param id 用户id
     * @return 用户信息
     */
    @Override
    public Person getById(Integer id) {
        return personMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据用户账号获取用户数据
     *
     * @param account 账号
     * @return
     */
    @Override
    public Person getByAccount(String account) {
        return personMapper.getByAccount(account);
    }

    /**
     * 根据条件查询用户数据
     *
     * @param criteria 用户信息查询条件
     * @return 用户信息集合
     */
    @Override
    public List<Person> selectByExample(PersonCriteria criteria) {
        return personMapper.selectByExample(criteria);
    }

    /**
     * 注册账号
     *
     * @param person 注册信息
     */
    @Override
    public void registerAccount(Person person) {
        personMapper.insertSelective(person);
    }
}
