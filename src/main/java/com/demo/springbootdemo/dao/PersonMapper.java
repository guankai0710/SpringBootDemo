package com.demo.springbootdemo.dao;

import com.demo.springbootdemo.entity.Person;
import com.demo.springbootdemo.entity.PersonCriteria;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PersonMapper {
    long countByExample(PersonCriteria example);

    int deleteByExample(PersonCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(Person record);

    int insertSelective(Person record);

    List<Person> selectByExample(PersonCriteria example);

    Person selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Person record, @Param("example") PersonCriteria example);

    int updateByExample(@Param("record") Person record, @Param("example") PersonCriteria example);

    int updateByPrimaryKeySelective(Person record);

    int updateByPrimaryKey(Person record);

    /**
     * 根据用户账号获取用户数据
     *
     * @param account 账号
     * @return
     */
    Person getByAccount(String account);
}