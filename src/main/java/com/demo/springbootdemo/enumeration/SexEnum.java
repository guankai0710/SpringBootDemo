package com.demo.springbootdemo.enumeration;

import com.demo.springbootdemo.exceptions.MyOwnRuntimeException;

/**
 * 性别枚举类
 *
 * @Author: guan.kai
 * @CreateTime: 2019/8/28 9:51
 * @Version: 1.0.0
 **/
public enum  SexEnum {

    /**
     * 女性
     **/
    WOMAN("0","女"),
    /**
     * 男性
     **/
    MAN("1","男");


    /**
     * 值
     **/
    private String value;

    /**
     * 描述
     **/
    private String doc;

    SexEnum(String value, String doc) {
        this.value = value;
        this.doc = doc;
    }

    /**
     * 根据值获取枚举对象
     *
     * @Author: guan.kai
     * @CreateTime: 2019/8/28 15:15
     * @Param: [value]
     * @Return: com.demo.springbootdemo.enumeration.SexEnum
     **/
    public static SexEnum getByValue(String value) throws MyOwnRuntimeException {
        for (SexEnum sexEnum : SexEnum.values()) {
            if (value.equals(sexEnum.getValue())){
                return sexEnum;
            }
        }
        throw new MyOwnRuntimeException("当前枚举类中没有这个值！");
    }

    /**
     * 根据描述获取枚举对象
     *
     * @Author: guan.kai
     * @CreateTime: 2019/8/28 15:16
     * @Param: [doc]
     * @Return: com.demo.springbootdemo.enumeration.SexEnum
     **/
    public static SexEnum getByDoc(String doc) throws MyOwnRuntimeException {
        for (SexEnum sexEnum : SexEnum.values()) {
            if (doc.equals(sexEnum.getDoc())){
                return sexEnum;
            }
        }
        throw new MyOwnRuntimeException("当前枚举类中没有这个描述！");
    }

    public String getValue() {
        return value;
    }

    public String getDoc() {
        return doc;
    }

}
