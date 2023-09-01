package com.demo.springbootdemo.appuul.http;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 描述：TODO
 *
 * @author guankai
 * @date 2023/4/24
 **/
@WebServlet(name = "appuul", urlPatterns = {"*.test","*.app"})
public class AppuulServlet extends HttpServlet {

    private AppuulRunner appuulRunner = new AppuulRunner();

    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //将request，和response放入上下文对象中
        appuulRunner.init(req, resp);
        try {
            //执行前置过滤
            appuulRunner.preRoute();
            //执行过滤
            appuulRunner.route();
            //执行后置过滤
            appuulRunner.postRoute();
        } catch (Throwable e) {
            RequestContext.getCurrentContext().getResponse().sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
        } finally {
            //清除变量
            RequestContext.getCurrentContext().unset();
        }
    }
}
