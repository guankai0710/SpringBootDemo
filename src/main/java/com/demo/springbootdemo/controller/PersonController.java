package com.demo.springbootdemo.controller;

import com.demo.springbootdemo.entity.Person;
import com.demo.springbootdemo.service.IPersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

    /**
     * 我的资料
     *
     * @param request
     * @param model
     * @return
     */
    @GetMapping("/mydata")
    public String mydata(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        Person person = (Person) session.getAttribute("person");
        model.addAttribute("person", personService.getByAccount(person.getAccount()));
        return "";
    }


}
