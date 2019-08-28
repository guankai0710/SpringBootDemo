package com.demo.springbootdemo.dao;

import com.demo.springbootdemo.entity.Department;
import com.demo.springbootdemo.entity.DepartmentCriteria;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface DepartmentMapper {
    long countByExample(DepartmentCriteria example);

    int deleteByExample(DepartmentCriteria example);

    int deleteByPrimaryKey(String id);

    int insert(Department record);

    int insertSelective(Department record);

    List<Department> selectByExample(DepartmentCriteria example);

    Department selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Department record, @Param("example") DepartmentCriteria example);

    int updateByExample(@Param("record") Department record, @Param("example") DepartmentCriteria example);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);
}