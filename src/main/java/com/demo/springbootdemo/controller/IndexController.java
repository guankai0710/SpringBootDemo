package com.demo.springbootdemo.controller;

import com.demo.springbootdemo.entity.Person;
import com.demo.springbootdemo.service.IPersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 首页
 *
 * @author: guan.kai
 * @date: 2019/8/31 23:18
 **/
@Controller
public class IndexController {

    @Resource
    private IPersonService personService;

    /**
     * 首页
     *
     * @param request
     * @param model
     * @return
     */
    @GetMapping("/index")
    public String index(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        Person user = (Person) session.getAttribute("user");
        model.addAttribute("userName",user.getName());
        return "index";
    }
}
