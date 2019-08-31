package com.demo.springbootdemo.entity;

import java.io.Serializable;
import java.util.Date;

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

    private Integer deleted;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Department(Integer id, Integer parentId, String name, Integer deleted, Date createTime) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.deleted = deleted;
        this.createTime = createTime;
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

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}