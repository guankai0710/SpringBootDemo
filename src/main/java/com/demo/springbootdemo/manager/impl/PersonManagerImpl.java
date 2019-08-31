package com.demo.springbootdemo.manager.impl;

import com.demo.springbootdemo.entity.Person;
import com.demo.springbootdemo.entity.PersonCriteria;
import com.demo.springbootdemo.manager.IPersonManager;
import com.demo.springbootdemo.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 用户业务操作接口
 *
 * @author: guan.kai
 * @date: 2019/8/28 14:34
 **/
@Component
@Transactional(rollbackFor = Exception.class)
public class PersonManagerImpl implements IPersonManager {

    @Autowired
    private IPersonService personService;

    /**
     * 根据用户id获取用户数据
     *
     * @param id 用户id
     * @return 用户信息
     */
    @Override
    public Person getById(Integer id) {
        return personService.getById(id);
    }

    /**
     * 根据用户账号获取用户数据
     *
     * @param account 账号
     * @return
     */
    @Override
    public Person getByAccount(String account) {
        return personService.getByAccount(account);
    }

    /**
     * 查询所有用户数据
     *
     * @return 用户信息集合
     */
    @Override
    public List<Person> selectAll() {
        PersonCriteria criteria = new PersonCriteria();
        return personService.selectByExample(criteria);
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
        personService.registerAccount(person);
    }
}
