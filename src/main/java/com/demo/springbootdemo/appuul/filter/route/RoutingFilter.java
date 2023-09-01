package com.demo.springbootdemo.appuul.filter.route;

import com.demo.springbootdemo.appuul.filter.AppuulFilter;
import com.demo.springbootdemo.appuul.http.RequestContext;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * 描述：转发请求，并且将返回值ResponseEntity放入全局threadlocal中
 *
 * @author guankai
 * @date 2023/4/24
 **/
public class RoutingFilter extends AppuulFilter {

    @Override
    public String filterType() {
        return "route";
    }    @Override
    public int filterOrder() {
        return 0;
    }
    @Override
    public void run(){
        RequestContext ctx = RequestContext.getCurrentContext();
        RequestEntity requestEntity = ctx.getRequestEntity();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity responseEntity = restTemplate.exchange(requestEntity,byte[].class);
        ctx.setResponseEntity(responseEntity);
    }
}
