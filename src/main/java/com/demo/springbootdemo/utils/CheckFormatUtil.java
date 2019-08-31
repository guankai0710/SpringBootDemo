package com.demo.springbootdemo.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * 格式校验工具类
 *
 * @author: guan.kai
 * @date: 2019/8/28 10:45
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
     * 密码正则表达式（8-18位字母加数字）
     **/
    public static final String PATTERN_PASSWORD = "^[a-zA-Z0-9]{8,18}$";

    /**
     * 手机号码校验
     *
     * @param mobileNo 手机号
     * @return 是否匹配
     */
    public static boolean isMobile(String mobileNo){
        return (StringUtils.isNotBlank(mobileNo) && mobileNo.matches(PATTERN_MOBILE));
    }

    /**
     * 邮箱地址校验
     *
     * @param email 邮箱地址
     * @return 是否匹配
     */
    public static boolean isEmail(String email){
        return (StringUtils.isNotBlank(email) && email.matches(PATTERN_EMAIL));
    }

    /**
     * 身份证号码校验
     *
     * @param idCard 身份证号码
     * @return 是否匹配
     */
    public static boolean isIdCard(String idCard){
        return (StringUtils.isNotBlank(idCard) && idCard.matches(PATTERN_IDCARD));
    }

    /**
     * 密码校验
     *
     * @param password 密码
     * @return 是否匹配
     */
    public static boolean isAccount(String password){
        return (StringUtils.isNotBlank(password) && password.matches(PATTERN_PASSWORD));
    }
}
