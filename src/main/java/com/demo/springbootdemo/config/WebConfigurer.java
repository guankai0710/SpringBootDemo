package com.demo.springbootdemo.config;

import com.demo.springbootdemo.config.intercepors.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * web拦截器配置类
 *
 * @Author: guan.kai
 * @CreateTime: 2019/8/29 17:31
 * @Version: 1.0.0
 **/
@Configuration
public class WebConfigurer implements WebMvcConfigurer {

    /**
     * 登录拦截器
     **/
    @Autowired
    private LoginInterceptor loginInterceptor;

    /**
     * 注册拦截器，我们自己写好的拦截器需要通过这里添加注册才能生效
     *
     * @Author: guan.kai
     * @CreateTime: 2019/8/29 17:33
     * @Param: [registry]
     * @Return: void
     **/
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // addPathPatterns("/**") 表示拦截所有的请求，
        // excludePathPatterns("/login", "/register") 表示除了登陆与注册之外，因为登陆注册不需要登陆也可以访问
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/login", "/register","/css/**","/js/**","/images/**");
//        super.addInterceptors(registry);    //较新Spring Boot的版本中这里可以直接去掉，否则会报错
    }

    /**
     * 配置静态资源的，比如html，js，css，等等
     *
     * @Author: guan.kai
     * @CreateTime: 2019/8/29 17:33
     * @Param: [registry]
     * @Return: void
     **/
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //addResourceHandler("/static/**") 设置访问路径前缀
        //addResourceLocations("classpath:/static/") 设置资源路径
//        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
}
