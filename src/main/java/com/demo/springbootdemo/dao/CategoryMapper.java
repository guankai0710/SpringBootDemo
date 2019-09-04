package com.demo.springbootdemo.dao;

import com.demo.springbootdemo.entity.Category;
import com.demo.springbootdemo.entity.CategoryCriteria;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CategoryMapper {
    long countByExample(CategoryCriteria example);

    int deleteByExample(CategoryCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    int insertSelective(Category record);

    List<Category> selectByExample(CategoryCriteria example);

    Category selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Category record, @Param("example") CategoryCriteria example);

    int updateByExample(@Param("record") Category record, @Param("example") CategoryCriteria example);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);
}