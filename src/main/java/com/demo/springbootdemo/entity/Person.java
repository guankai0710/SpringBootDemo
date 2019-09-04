package com.demo.springbootdemo.entity;

import java.io.Serializable;
import java.util.Date;

public class Person implements Serializable {
    private Integer id;

    private String name;

    private String account;

    private String password;

    private Integer age;

    private String sex;

    private String tel;

    private String email;

    private String address;

    private Integer deleted;

    private Date createTime;

    private String sexDoc;

    private static final long serialVersionUID = 1L;

    public Person(Integer id, String name, String account, String password, Integer age, String sex, String tel, String email, String address, Integer deleted, Date createTime) {
        this.id = id;
        this.name = name;
        this.account = account;
        this.password = password;
        this.age = age;
        this.sex = sex;
        this.tel = tel;
        this.email = email;
        this.address = address;
        this.deleted = deleted;
        this.createTime = createTime;
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

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
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

    public String getSexDoc() {
        return sexDoc;
    }

    public void setSexDoc(String sexDoc) {
        this.sexDoc = sexDoc;
    }
}