package com.demo.springbootdemo.entity;

import java.io.Serializable;

/**
 * 人员信息
 *
 * @Author: guan.kai
 * @CreateTime: 2019/8/19 22:04
 * @Version: 0.0.1
 **/
public class Person implements Serializable {
    private String id;

    private String name;

    private String departmentId;

    private String age;

    private String sex;

    private String tel;

    private String address;

    private static final long serialVersionUID = 1L;

    public Person(String id, String name, String departmentId, String age, String sex, String tel, String address) {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId == null ? null : departmentId.trim();
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age == null ? null : age.trim();
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

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", departmentId='" + departmentId + '\'' +
                ", age='" + age + '\'' +
                ", sex='" + sex + '\'' +
                ", tel='" + tel + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}