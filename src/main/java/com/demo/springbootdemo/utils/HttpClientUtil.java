package com.demo.springbootdemo.utils;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * HTTP请求工具类
 *
 * @author: guan.kai
 * @date: 2019/9/11 15:12
 **/
public class HttpClientUtil {

    private static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

    /** 从连接时获取时间*/
    private static int CONNECTION_REQUEST_TIMEOUT = 1000;

    /** 建立连接时间（三次握手时间）*/
    private static int CONNECTION_TIMEOUT = 2000;

    /** 服务器返回数据时间*/
    private static int SOCKET_TIMEOUT = 50000;

    /** HttpClient 连接池*/
    private static PoolingHttpClientConnectionManager cm = null;

    /** 异常返回结果*/
    private static final Map<String,Object> ERROR_MAP = new HashMap<>();


    static {
        // 初始化连接池，可用于请求HTTP/HTTPS（信任所有证书）
        cm = new PoolingHttpClientConnectionManager(getRegistry());
        // 整个连接池最大连接数
        cm.setMaxTotal(100);
        // 每路由最大连接数，默认值是2
        cm.setDefaultMaxPerRoute(5);

        //初始化异常返回结果
        ERROR_MAP.put("code",500);
        ERROR_MAP.put("msg","请求失败，请联系管理员");
        ERROR_MAP.put("data",new HashMap<>());
    }

    /**
     * 获取 HTTPClient注册器
     *
     * @return
     * @throws Exception
     */
    private static Registry<ConnectionSocketFactory> getRegistry() {
        Registry<ConnectionSocketFactory> registry = null;

        try {
            registry = RegistryBuilder.<ConnectionSocketFactory> create()
                    .register("http", new PlainConnectionSocketFactory()).register("https", getSSLFactory()).build();
        } catch (Exception e) {
            logger.error("获取 HTTPClient注册器失败",e);
        }
        return registry;
    }

    /**
     * 获取HTTPS SSL连接工厂
     * 跳过证书校验，即信任所有证书
     *
     * @throws Exception
     * @return
     */
    private static SSLConnectionSocketFactory getSSLFactory() throws Exception {
        // 设置HTTPS SSL证书信息，跳过证书校验，即信任所有证书请求HTTPS
        SSLContextBuilder sslBuilder = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
            @Override
            public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                return true;
            }
        });

        // 获取HTTPS SSL证书连接上下文
        SSLContext sslContext = sslBuilder.build();
        // 获取HTTPS连接工厂
        SSLConnectionSocketFactory sslCsf = new SSLConnectionSocketFactory(sslContext,
                new String[] { "SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.2" }, null, NoopHostnameVerifier.INSTANCE);
        return sslCsf;
    }

    /**
     * 发送 HTTP GET请求
     * 不带请求参数和请求头
     *
     * @param url 地址
     * @return
     */
    public static Map<String,Object> httpGet(String url) {
        try {
            HttpGet httpGet = new HttpGet(url);
            Map<String,Object> resultMap = JSON.parseObject(doHttp(httpGet),Map.class);
            return resultMap;
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return ERROR_MAP;
        }
    }

    /**
     * 发送 HTTP GET请求
     * 带请求参数，不带请求头
     *
     * @param url 地址
     * @param params 参数
     * @return
     */
    public static Map<String,Object> httpGet(String url, Map<String, Object> params) {
        try {
            // 转换请求参数
            List<NameValuePair> pairs = covertParams2NVPS(params);
            // 装载请求地址和参数
            URIBuilder ub = new URIBuilder();
            ub.setPath(url);
            ub.setParameters(pairs);

            HttpGet httpGet = new HttpGet(ub.build());
            Map<String,Object> resultMap = JSON.parseObject(doHttp(httpGet),Map.class);
            return resultMap;
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return ERROR_MAP;
        }
    }

    /**
     * 发送 HTTP GET请求
     * 带请求参数和请求头
     *
     * @param url 地址
     * @param headers 请求头
     * @param params 参数
     * @return
     */
    public static Map<String,Object> httpGet(String url, Map<String, Object> headers, Map<String, Object> params) {
        try {
            // 转换请求参数
            List<NameValuePair> pairs = covertParams2NVPS(params);
            // 装载请求地址和参数
            URIBuilder ub = new URIBuilder();
            ub.setPath(url);
            ub.setParameters(pairs);

            HttpGet httpGet = new HttpGet(ub.build());
            // 设置请求头
            for (Map.Entry<String, Object> param : headers.entrySet()) {
                httpGet.addHeader(param.getKey(), String.valueOf(param.getValue()));
            }
            Map<String,Object> resultMap = JSON.parseObject(doHttp(httpGet),Map.class);
            return resultMap;
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return ERROR_MAP;
        }
    }

    /**
     * 发送 HTTP POST请求
     * 不带请求参数和请求头
     *
     * @param url 地址
     * @return
     */
    public static Map<String,Object> httpPost(String url) {
        try {
            HttpPost httpPost = new HttpPost(url);
            Map<String,Object> resultMap = JSON.parseObject(doHttp(httpPost),Map.class);
            return resultMap;
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return ERROR_MAP;
        }
    }

    /**
     * 发送 HTTP POST请求
     * 带请求参数，不带请求头
     *
     * @param url 地址
     * @param params 参数
     * @return
     */
    public static Map<String,Object> httpPost(String url, Map<String, Object> params) {
        try {
            // 转换请求参数
            List<NameValuePair> pairs = covertParams2NVPS(params);
            HttpPost httpPost = new HttpPost(url);
            // 设置请求参数
            httpPost.setEntity(new UrlEncodedFormEntity(pairs, StandardCharsets.UTF_8.name()));

            Map<String,Object> resultMap = JSON.parseObject(doHttp(httpPost),Map.class);
            return resultMap;
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return ERROR_MAP;
        }
    }

    /**
     * 发送 HTTP POST请求
     * 带请求参数和请求头
     *
     * @param url 地址
     * @param headers 请求头
     * @param params 参数
     * @return
     */
    public static Map<String,Object> httpPost(String url, Map<String, Object> headers, Map<String, Object> params) {
        try {
            // 转换请求参数
            List<NameValuePair> pairs = covertParams2NVPS(params);
            HttpPost httpPost = new HttpPost(url);
            // 设置请求参数
            httpPost.setEntity(new UrlEncodedFormEntity(pairs, StandardCharsets.UTF_8.name()));
            // 设置请求头
            for (Map.Entry<String, Object> param : headers.entrySet()){
                httpPost.addHeader(param.getKey(), String.valueOf(param.getValue()));
            }

            Map<String,Object> resultMap = JSON.parseObject(doHttp(httpPost),Map.class);
            return resultMap;
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return ERROR_MAP;
        }
    }

    /**
     * 发送 HTTP POST请求，参数格式JSON
     * 请求参数是JSON格式，数据编码是UTF-8
     *
     * @param url 地址
     * @param param 参数
     * @return
     */
    public static Map<String,Object> httpPostJson(String url, String param) {
        try {
            HttpPost httpPost = new HttpPost(url);
            // 设置请求头
            httpPost.addHeader("Content-Type", "application/json; charset=UTF-8");
            // 设置请求参数
            httpPost.setEntity(new StringEntity(param, StandardCharsets.UTF_8.name()));

            Map<String,Object> resultMap = JSON.parseObject(doHttp(httpPost),Map.class);
            return resultMap;
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return ERROR_MAP;
        }
    }

    /**
     * application/x-www-form-urlencoded
     *
     * @param url 地址
     * @param params 参数
     * @return
     */
    public static Map<String,Object> httpPostXwwwform(String url, Map<String, Object> params) {
        try {
            // 转换请求参数
            List<NameValuePair> pairs = covertParams2NVPS(params);
            HttpPost httpPost = new HttpPost(url);

            // 设置请求参数
            httpPost.setEntity(new UrlEncodedFormEntity(pairs, StandardCharsets.UTF_8.name()));
            // 设置请求头
            httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");

            Map<String,Object> resultMap = JSON.parseObject(doHttp(httpPost),Map.class);
            return resultMap;
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return ERROR_MAP;
        }
    }

    /**
     * multipart/form-data
     *
     * @param url 地址
     * @param params 参数
     * @param files 文件，没有给 null 值
     * @return
     */
    public static Map<String,Object> httpPostFormdata(String url, Map<String, Object> params, Map<String, File> files) {
        try {
            HttpPost httpPost = new HttpPost(url);

            //设置请求参数
            Map<String, ContentBody> reqParam = new HashMap<>(16);
            if (params != null){
                for (Map.Entry<String, Object> param : params.entrySet()) {
                    if (param.getValue() != null){
                        reqParam.put(param.getKey(),new StringBody(param.getValue().toString(), ContentType.MULTIPART_FORM_DATA));
                    }
                }
            }
            if (files != null){
                for (Map.Entry<String, File> file : files.entrySet()) {
                    reqParam.put(file.getKey(),new FileBody(file.getValue()));
                }
            }
            MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
            for(Map.Entry<String, ContentBody> param : reqParam.entrySet()){
                multipartEntityBuilder.addPart(param.getKey(), param.getValue());
            }
            HttpEntity reqEntity = multipartEntityBuilder.build();
            httpPost.setEntity(reqEntity);

            Map<String,Object> resultMap = JSON.parseObject(doHttp(httpPost),Map.class);
            return resultMap;
        } catch (Exception e) {

            return ERROR_MAP;
        }
    }

    /**
     * 将Map键值对拼接成QueryString字符串，UTF-8编码
     *
     * @param params
     * @return
     * @throws Exception
     */
    public static String getQueryStr(Map<String, Object> params) throws Exception {
        return URLEncodedUtils.format(covertParams2NVPS(params), StandardCharsets.UTF_8.name());
    }

    /**
     * 发送 HTTP 请求
     *
     * @param request
     * @return
     * @throws Exception
     */
    private static String doHttp(HttpRequestBase request) throws Exception {
        RequestConfig.Builder builder = RequestConfig.custom();
        RequestConfig config = builder.setSocketTimeout(SOCKET_TIMEOUT).setConnectTimeout(CONNECTION_TIMEOUT)
                .setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT).build();
        // 通过连接池获取连接对象
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(config).setConnectionManager(cm)
                .build();

        return doRequest(httpClient, request);
    }

    /**
     * 处理Http/Https请求，并返回请求结果
     *
     * 注：默认请求编码方式 UTF-8
     *
     * @param httpClient
     * @param request
     * @return 请求结果
     * @throws Exception
     */
    private static String doRequest(CloseableHttpClient httpClient, HttpRequestBase request) throws Exception {
        String result = null;
        CloseableHttpResponse response = null;

        try {
            // 获取请求结果
            response = httpClient.execute(request);
            // 解析请求结果
            HttpEntity entity = response.getEntity();
            // 转换结果
            result = EntityUtils.toString(entity, StandardCharsets.UTF_8.name());
            // 关闭IO流
            EntityUtils.consume(entity);
        } finally {
            if (null != response){
                response.close();
            }
        }

        return result;
    }

    /**
     * 转换请求参数
     *
     * @param params map参数
     * @return nameValuePair集合
     */
    private static List<NameValuePair> covertParams2NVPS(Map<String, Object> params) {
        List<NameValuePair> pairs = new ArrayList<NameValuePair>();

        for (Map.Entry<String, Object> param : params.entrySet()) {
            pairs.add(new BasicNameValuePair(param.getKey(), String.valueOf(param.getValue())));
        }
        return pairs;
    }

    /**
     * 下载文件方法 InputStream
     */
    public static String httpPostJsonFile(String url, String param, String path, String FilenNme) throws Exception {
        HttpPost httpPost = new HttpPost(url);
        // 设置请求头
        httpPost.addHeader("Content-Type", "application/json; charset=UTF-8");
        // 设置请求参数
        httpPost.setEntity(new StringEntity(param, StandardCharsets.UTF_8.name()));
        RequestConfig.Builder builder = RequestConfig.custom();
        RequestConfig config = builder.setSocketTimeout(SOCKET_TIMEOUT).setConnectTimeout(CONNECTION_TIMEOUT)
                .setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT).build();
        // 通过连接池获取连接对象
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(config).setConnectionManager(cm)
                .build();
        InputStream out = null;
        CloseableHttpResponse response = null;
        try {
            // 获取请求结果
            response = httpClient.execute(httpPost);
            // 解析请求结果
            HttpEntity entity = response.getEntity();
            // 获取文件流
            out = entity.getContent();
            // 可以是任何图片格式.jpg,.png等
            File file = new File(path, FilenNme + ".jpg");
            FileOutputStream fos = new FileOutputStream(file);
            byte[] b = new byte[1024];
            int nRead = 0;
            while ((nRead = out.read(b)) != -1) {
                fos.write(b, 0, nRead);
            }
            fos.flush();
            fos.close();
            out.close();
            // 关闭IO流
            EntityUtils.consume(entity);
        }finally {
            if (null != response) {
                response.close();
            }
        }
        return path + "/" + FilenNme + ".jpg";
    }

}
