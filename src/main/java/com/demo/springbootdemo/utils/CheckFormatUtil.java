package com.demo.springbootdemo.utils;

import com.demo.springbootdemo.constant.FormatConstant;
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
     * 手机号码校验
     *
     * @Author: guan.kai
     * @CreateTime: 2019/8/28 11:04
     * @Param: [mobileNo]
     * @Return: boolean
     **/
    public static boolean isMobile(String mobileNo){
        return (StringUtils.isNotBlank(mobileNo) && mobileNo.matches(FormatConstant.Regex.PATTERN_MOBILE));
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
        return (StringUtils.isNotBlank(email) && email.matches(FormatConstant.Regex.PATTERN_EMAIL));
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
        return (StringUtils.isNotBlank(idCard) && idCard.matches(FormatConstant.Regex.PATTERN_IDCARD));
    }
}
