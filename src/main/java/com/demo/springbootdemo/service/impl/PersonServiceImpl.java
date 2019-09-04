package com.demo.springbootdemo.service.impl;

import com.demo.springbootdemo.dao.PersonMapper;
import com.demo.springbootdemo.entity.Person;
import com.demo.springbootdemo.entity.PersonCriteria;
import com.demo.springbootdemo.service.IPersonService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 用户操作接口
 *
 * @author: guan.kai
 * @date: 2019/8/14 22:21
 **/
@Service
@Transactional(rollbackFor = Exception.class)
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
     * 查询所有用户数据
     *
     * @return 用户信息集合
     */
    @Override
    public List<Person> selectAll() {
        return personMapper.selectByExample(new PersonCriteria());
    }

    /**
     * 注册账号
     *
     * @param account 账号
     * @param password 密码
     */
    @Override
    public void registerAccount(String account, String password) {
        Person person = new Person();
        person.setAccount(account);
        person.setPassword(password);
        person.setCreateTime(new Date());
        personMapper.insertSelective(person);
    }
}
