package com.demo.springbootdemo.entity;

import java.io.Serializable;

/**
 * 人员信息
 *
 * @author: guan.kai
 * @date: 2019/8/29 22:15
 **/
public class Person implements Serializable {

    private Integer id;

    private String name;

    private Integer departmentId;

    private Integer age;

    private String sex;

    private String tel;

    private String address;

    private String sexDoc;

    private static final long serialVersionUID = 1L;

    public Person(Integer id, String name, Integer departmentId, Integer age, String sex, String tel, String address) {
        this.id = id;
        this.name = name;
        this.departmentId = departmentId;
        this.age = age;
        this.sex = sex;
        this.tel = tel;
        this.address = address;
    }

    public Person() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getSexDoc() {
        return sexDoc;
    }

    public void setSexDoc(String sexDoc) {
        this.sexDoc = sexDoc;
    }
}