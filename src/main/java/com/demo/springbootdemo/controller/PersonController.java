package com.demo.springbootdemo.controller;

import com.demo.springbootdemo.entity.Person;
import com.demo.springbootdemo.enumeration.SexEnum;
import com.demo.springbootdemo.exceptions.MyOwnRuntimeException;
import com.demo.springbootdemo.service.IPersonService;
import com.demo.springbootdemo.vo.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
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
     * @return
     */
    @GetMapping("/mydata")
    public JsonResult mydata(HttpServletRequest request){
        JsonResult result = new JsonResult();
        HttpSession session = request.getSession();
        Person user = (Person) session.getAttribute("person");
        Person person = personService.getByAccount(user.getAccount());
        if (person == null){
            result.setMsg("获取失败！");
        }
        try {
            person.setSexDoc(SexEnum.getByValue(person.getSex()).getDoc());
        } catch (MyOwnRuntimeException e) {
            logger.error(e.getMessage());
        }
        result.setSuccess(true);
        result.setObj(person);
        return result;
    }


}
