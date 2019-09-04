package com.demo.springbootdemo.controller;

import com.demo.springbootdemo.entity.Person;
import com.demo.springbootdemo.service.IPersonService;
import com.demo.springbootdemo.utils.CheckFormatUtil;
import com.demo.springbootdemo.vo.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 登录、注册
 *
 * @author: guan.kai
 * @date: 2019/8/30 9:13
 **/
@Controller
public class LoginController {

    @Resource
    private IPersonService personService;

    /**
     * 登录页面
     *
     * @return
     */
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    /**
     * 登录操作
     *
     * @param request
     * @param account 账号
     * @param password 密码
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    public JsonResult login(HttpServletRequest request, String account, String password){
        JsonResult result = new JsonResult();
        Person person = personService.getByAccount(account);
        if (person != null && password.equals(person.getPassword())){
            HttpSession session = request.getSession();
            session.setAttribute("person",person);
            result.setSuccess(true);
            result.setMsg("登录成功！");
        }else {
            result.setMsg("用户名密码错误！！");
        }
        return result;
    }

    /**
     * 注册页面
     *
     * @return
     */
    @GetMapping("/register")
    public String register(){
        return "register";
    }

    /**
     * 注册操作
     *
     * @param account 账号
     * @param password 密码
     * @return
     */
    @PostMapping("/register")
    @ResponseBody
    public JsonResult register(String account, String password){
        JsonResult result = new JsonResult();
        Person person = personService.getByAccount(account);
        if (person != null){
            result.setMsg("该账号已存在！");
            return result;
        }
        if (!CheckFormatUtil.isAccount(password)){
            result.setMsg("密码必须由8-18位字母加数字组成！");
            return result;
        }
        personService.registerAccount(account,password);
        result.setSuccess(true);
        result.setMsg("注册成功！");
        return result;
    }

    /**
     * 退出登录
     *
     * @param request
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute("person");
        return "login";
    }
}
