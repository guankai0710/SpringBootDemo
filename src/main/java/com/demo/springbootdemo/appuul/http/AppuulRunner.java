package com.demo.springbootdemo.appuul.http;



import com.demo.springbootdemo.appuul.filter.AppuulFilter;
import com.demo.springbootdemo.appuul.filter.post.SendResponseFilter;
import com.demo.springbootdemo.appuul.filter.pre.RequestWrapperFilter;
import com.demo.springbootdemo.appuul.filter.route.RoutingFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 描述：TODO
 *
 * @author guankai
 * @date 2023/4/24
 **/
public class AppuulRunner {

    /**
     * 静态写死过滤器
     */
    private ConcurrentHashMap<String, List<AppuulFilter>> hashFiltersByType = new ConcurrentHashMap<String, List<AppuulFilter>>(){{
        put("pre",new ArrayList<AppuulFilter>(){{
            add(new RequestWrapperFilter());
        }});
        put("route",new ArrayList<AppuulFilter>(){{
            add(new RoutingFilter());
        }});
        put("post",new ArrayList<AppuulFilter>(){{
            add(new SendResponseFilter());
        }});
    }};

    public void init(HttpServletRequest req, HttpServletResponse resp) {
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.setRequest(req);
        ctx.setResponse(resp);
    }

    public void preRoute() throws Throwable {
        runFilters("pre");
    }

    public void route() throws Throwable{
        runFilters("route");
    }

    public void postRoute() throws Throwable{
        runFilters("post");
    }


    public void runFilters(String sType) throws Throwable {
        List<AppuulFilter> list = this.hashFiltersByType.get(sType);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                AppuulFilter zuulFilter = list.get(i);
                zuulFilter.run();
            }
        }
    }

}
