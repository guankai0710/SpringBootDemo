package com.demo.springbootdemo.controller;

import com.alibaba.fastjson.JSON;
import com.demo.springbootdemo.entity.Person;
import com.demo.springbootdemo.entity.PersonCriteria;
import com.demo.springbootdemo.service.IPersonService;
import com.demo.springbootdemo.utils.ExcelUtil;
import com.demo.springbootdemo.utils.MyCollectionsUitl;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户操作接口
 *
 * @Author: guan.kai
 * @CreateTime: 2019/8/14 22:15
 * @Version: 0.0.1
 **/
@Controller
@RequestMapping("/person")
public class PersonController {

    @Resource
    private IPersonService personService;

    @RequestMapping("/show")
    @ResponseBody
    public String show(){
        Person person = personService.getById("337617ab5fb54206abc1e908784e6d9572889dcd873743fdb3056f9bd3701e21");
        PersonCriteria criteria = new PersonCriteria();
        List<Person> personList = personService.selectByExample(criteria);
        List<String> collect = personList.stream().map(Person::getAddress).collect(Collectors.toList());
        List<List<Person>> lists = MyCollectionsUitl.subList(personList, 1);
        System.out.println("person:====:"+person.toString());
        String jsonStr = JSON.toJSONString(personList);
        return jsonStr;
    }

    public void exportExcel(HttpServletResponse response){
        String fileName = "测试导出表格.xlsx";
        String sheetName = "测试导出";
        String[] title = new String[2];
        String[][] content = new String[2][3];
        int[] colWidth = new int[2];
        ExcelUtil.exportExcel(response,fileName,sheetName,title,content,colWidth);
    }

}
