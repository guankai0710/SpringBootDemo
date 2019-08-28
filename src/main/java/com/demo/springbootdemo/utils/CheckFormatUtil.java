package com.demo.springbootdemo.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * 格式校验工具类
 *
 * @Author: guan.kai
 * @CreateTime: 2019/8/28 10:45
 * @Version: 1.0.0
 **/
public class CheckFormatUtil {

    private CheckFormatUtil(){}

    /**
     * 手机号码正则表达式
     **/
    public static final String PATTERN_MOBILE = "^1[3|4|5|6|7|8|9][0-9]{9}$";
    /**
     * 邮箱号码正则表达式
     **/
    public static final String PATTERN_EMAIL = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
    /**
     * 身份证号码正则表达式
     **/
    public static final String PATTERN_IDCARD = "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$|^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$";

    /**
     * 手机号码校验
     *
     * @Author: guan.kai
     * @CreateTime: 2019/8/28 11:04
     * @Param: [mobileNo]
     * @Return: boolean
     **/
    public static boolean isMobile(String mobileNo){
        return (StringUtils.isNotBlank(mobileNo) && mobileNo.matches(PATTERN_MOBILE));
    }

    /**
     * 邮箱地址校验
     *
     * @Author: guan.kai
     * @CreateTime: 2019/8/28 11:06
     * @Param: [email]
     * @Return: boolean
     **/
    public static boolean isEmail(String email){
        return (StringUtils.isNotBlank(email) && email.matches(PATTERN_EMAIL));
    }

    /**
     * 身份证号码校验
     *
     * @Author: guan.kai
     * @CreateTime: 2019/8/28 11:24
     * @Param: [idCard]
     * @Return: boolean
     **/
    public static boolean isIdCard(String idCard){
        return (StringUtils.isNotBlank(idCard) && idCard.matches(PATTERN_IDCARD));
    }
}
