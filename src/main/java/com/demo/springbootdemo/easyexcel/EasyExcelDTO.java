package com.demo.springbootdemo.easyexcel;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 描述：TODO
 *
 * @author guankai
 * @date 2022/12/14
 **/
public class EasyExcelDTO  {

    @ExcelIgnore
    private Integer id;

    @ExcelProperty(value = "名称")
    @NotBlank(message = "[名称]不能为空")
    private String name;

    @ExcelProperty(value = "年龄")
    @NotNull(message = "[年龄]不能为空")
    private Integer age;

    @ExcelProperty(value = "性别")
    private Integer sex;
}
