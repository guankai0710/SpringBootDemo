package com.demo.springbootdemo.constant;

/**
 * 格式校验常量
 *
 * @Author: guan.kai
 * @CreateTime: 2019/8/28 9:51
 * @Version: 1.0.0
 **/
public class FormatConstant {
    private FormatConstant() {
    }

    /**
     * 正则表达式
     *
     * @Author: guan.kai
     * @CreateTime: 2019/8/28 10:33
     * @Param:
     * @Return:
     **/
    public static final class Regex{
        private Regex(){}
        /**
         * 手机号码
         **/
        public static final String PATTERN_MOBILE = "^1[3|4|5|6|7|8|9][0-9]{9}$";
        /**
         * 邮箱号码
         **/
        public static final String PATTERN_EMAIL = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
        /**
         * 身份证号码
         **/
        public static final String PATTERN_IDCARD = "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$|^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$";
    }

}
