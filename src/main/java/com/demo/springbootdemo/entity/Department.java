package com.demo.springbootdemo.entity;

import java.io.Serializable;

/**
 * 部门信息
 *
 * @Author: guan.kai
 * @CreateTime: 2019/8/19 22:04
 * @Version: 0.0.1
 **/
public class Department implements Serializable {
    private String id;

    private String parentId;

    private String name;

    private static final long serialVersionUID = 1L;

    public Department(String id, String parentId, String name) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
    }

    public Department() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}