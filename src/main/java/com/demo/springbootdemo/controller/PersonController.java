package com.demo.springbootdemo.controller;

import com.demo.springbootdemo.entity.Person;
import com.demo.springbootdemo.enumeration.SexEnum;
import com.demo.springbootdemo.exceptions.MyOwnRuntimeException;
import com.demo.springbootdemo.service.IPersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户操作接口
 *
 * @author: guan.kai
 * @date: 2019/8/14 22:15
 **/
@Controller
@RequestMapping("/person")
public class PersonController {
    private static final Logger logger = LoggerFactory.getLogger(PersonController.class);

    @Resource
    private IPersonService personService;

    @GetMapping("/show")
    @ResponseBody
    public String show(Model model, String id){
        Person person = personService.getById(Integer.valueOf(id));
        if (person != null){
            try {
                person.setSexDoc(SexEnum.getByValue(person.getSex()).getDoc());
            } catch (MyOwnRuntimeException e) {
                logger.error(e.getMessage());
            }
        }
        model.addAttribute("person", person);
        return "";
    }

    @GetMapping("/all")
    @ResponseBody
    public String all(Model model){
        List<Person> personList = personService.selectAll();
        model.addAttribute("personList", personList);
        return "";
    }

}
