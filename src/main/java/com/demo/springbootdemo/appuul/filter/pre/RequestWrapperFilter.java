package com.demo.springbootdemo.appuul.filter.pre;

import com.demo.springbootdemo.appuul.filter.AppuulFilter;
import com.demo.springbootdemo.appuul.http.RequestContext;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StreamUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述：前置执行过滤器，负责封装请求
 *
 * @author guankai
 * @date 2023/4/24
 **/
public class RequestWrapperFilter extends AppuulFilter {

    @Override
    public String filterType() {
        return "pre";
    }
    @Override
    public int filterOrder() {
        return -1;
    }
    @Override
    public void run() throws Exception{

        String rootURL = "http://localhost:8080";
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest servletRequest = ctx.getRequest();

        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload sf = new ServletFileUpload(factory);
        if (!ServletFileUpload.isMultipartContent(servletRequest)) {
           return;
        }
        Map<String,String> requiredFieldMap = new HashMap<String, String>();
        for (FileItem fi : sf.parseRequest(servletRequest)) {
            if (fi.isFormField() && "token".equals(fi.getFieldName())) {
                requiredFieldMap.put(fi.getFieldName(), fi.getString("UTF-8"));
            }
        }


        String targetURL = rootURL + servletRequest.getRequestURI();
        RequestEntity<byte[]> requestEntity = null;
        try {
            requestEntity = createRequestEntity(servletRequest, targetURL);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //4、将requestEntity放入全局threadlocal之中
        ctx.setRequestEntity(requestEntity);
    }

    private RequestEntity createRequestEntity(HttpServletRequest request,String url) throws URISyntaxException, IOException {
        String method = request.getMethod();
        HttpMethod httpMethod = HttpMethod.resolve(method);
        //1、封装请求头
        MultiValueMap<String, String> headers =createRequestHeaders(request);
        //2、封装请求体
        byte[] body = createRequestBody(request);
        //3、构造出RestTemplate能识别的RequestEntity
        RequestEntity requestEntity = new RequestEntity<byte[]>(body,headers,httpMethod, new URI(url));
        return requestEntity;
    }

    private byte[] createRequestBody(HttpServletRequest request) throws IOException {
        InputStream inputStream = request.getInputStream();
        return StreamUtils.copyToByteArray(inputStream);
    }

    private MultiValueMap<String, String> createRequestHeaders(HttpServletRequest request) {
        HttpHeaders headers = new HttpHeaders();
        List<String> headerNames = Collections.list(request.getHeaderNames());
        for(String headerName:headerNames) {
            List<String> headerValues = Collections.list(request.getHeaders(headerName));
            for(String headerValue:headerValues) {
                headers.add(headerName, headerValue);
            }
        }
        return headers;
    }

}
