package com.demo.springbootdemo.enumeration;

/**
 * 性别枚举类
 *
 * @Author: guan.kai
 * @CreateTime: 2019/8/28 9:51
 * @Version: 1.0.0
 **/
public enum  SexEnum {

    //女
    WOMAN("0","女"),
    //男
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

    public static SexEnum getByValue(String value){
        for (SexEnum sexEnum : SexEnum.values()) {
            if (value.equals(sexEnum.getValue())){
                return sexEnum;
            }
        }
        throw new RuntimeException("当前枚举类中没有这个值！");
    }

    public static SexEnum getByDoc(String doc){
        for (SexEnum sexEnum : SexEnum.values()) {
            if (doc.equals(sexEnum.getDoc())){
                return sexEnum;
            }
        }
        throw new RuntimeException("当前枚举类中没有这个描述！");
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }
}
