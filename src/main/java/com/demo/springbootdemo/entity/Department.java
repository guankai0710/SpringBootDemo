package com.demo.springbootdemo.entity;

import java.io.Serializable;

/**
 * 部门信息
 *
 * @author: guan.kai
 * @date: 2019/8/29 22:15
 **/
public class Department implements Serializable {

    private Integer id;

    private Integer parentId;

    private String name;

    private static final long serialVersionUID = 1L;

    public Department(Integer id, Integer parentId, String name) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
    }

    public Department() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}