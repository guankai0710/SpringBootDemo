package com.demo.springbootdemo.appuul.filter;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 描述：TODO
 *
 * @author guankai
 * @date 2023/4/24
 **/
@WebFilter(urlPatterns = {"*.test","*.app"})
public class ReadBodyHttpServletFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        RepeatedlyReadRequestWrapper requestWrapper = new RepeatedlyReadRequestWrapper(httpServletRequest);
        filterChain.doFilter(requestWrapper, httpServletResponse);
    }

    @Override
    public void destroy() {

    }
}
