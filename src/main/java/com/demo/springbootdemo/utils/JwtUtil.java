package com.demo.springbootdemo.utils;

import io.jsonwebtoken.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * jwt工具类
 *
 * 依赖 io.jsonwebtoken.jjwt 包
 * 依赖 commons-codec.commons-codec 包
 *
 * @author: guan.kai
 * @date: 2020/7/28 11:00
 **/
public class JwtUtil {

    private JwtUtil() { }

    /**
     * Token过期时间  必须大于生效时间  当前3分钟
     */
    private static final Long TOKEN_EXPIRE_TIME = 3 * 60 * 1000L;

    /**
     * Token 加密/解密 盐
     */
    private static final String TOKEN_SECRET = "gduhishfHIjDDIWIjiajfJDIJIjjo";

    /**
     * 加密类型 三个值可取 HS256  HS384  HS512
     */
    private static final SignatureAlgorithm JWT_ALG = SignatureAlgorithm.HS256;

    /**
     * 添加一个前缀
     */
    private static final String JWT_SEPARATOR = "Jwt#";

    /**
     * token生效时间(默认是从当前开始生效)
     * 默认：new Date(System.currentTimeMillis() + START_TIME)
     */
    private static final Long START_TIME = 0L;

    /**
     * token在什么时间之前是不可用的（默认从当前时间）
     * 默认：new Date(System.currentTimeMillis() + BEFORE_TIME)
     */
    private static final Long  BEFORE_TIME = 0L;

    private static Key generateKey() {
        // 将将密码转换为字节数组
        byte[] bytes = Base64.decodeBase64(TOKEN_SECRET);
        // 根据指定的加密方式，生成密钥
        return new SecretKeySpec(bytes, JWT_ALG.getJcaName());
    }

    /**
     * 创建token
     *
     * @param sub token所面向的用户
     * @param aud 接收token的一方
     * @param jti token的唯一身份标识，主要用来作为一次性token,从而回避重放攻击
     * @param iss token签发者
     * @param map 自定义信息的存储
     * @return 加密后的token字符串
     */
    public static String createToken(String sub, String aud, String jti, String iss, Map<String, Object> map){
        final JwtBuilder builder = Jwts.builder();

        // 设置头部信息
        Map<String, Object> header = new HashMap<>(2);
        header.put("Type", "Jwt");
        header.put("alg", "HS256");

        if (map != null && !map.isEmpty()){
            builder.setClaims(map);
        }

        String token = builder
                .setHeader(header)
                .signWith(JWT_ALG, generateKey())
                .setSubject(sub)
                .setAudience(aud)
                .setId(jti)
                .setIssuer(iss)
                .setNotBefore(new Date(System.currentTimeMillis() + BEFORE_TIME))
                .setIssuedAt(new Date(System.currentTimeMillis() + START_TIME))
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRE_TIME))
                .compact();
        return JWT_SEPARATOR + token;
    }

    /**
     * 创建token
     * @param sub token所面向的用户
     * @param aud 接收token的一方
     * @param map 自定义信息存储
     * @return token 字符串
     */
    public static String createToken(String sub, String aud, Map<String, Object> map){
        return createToken(sub, aud, new Date().toString(),null,map);
    }

    /**
     * 创建token
     * @param sub token所面向的用户
     * @param map 自定义信息存储
     * @return token字符串
     */
    public static String createToken(String sub, Map<String,Object> map){
        return createToken(sub,null,map);
    }

    /**
     * 创建token
     * @param sub token所面向的用户
     * @return token字符串
     */
    public static String createToken(String sub){
        return createToken(sub, null);
    }

    /**
     * 解析token
     * 可根据Jws<Claims>   获取  header|body|getSignature三部分数据
     * @param token token字符串
     * @return Jws
     */
    public static Jws<Claims> parseToken(String token) {
        // 移除 token 前的"Bearer#"字符串
        token = StringUtils.substringAfter(token, JWT_SEPARATOR);
        // 解析 token 字符串
        return Jwts.parser().setSigningKey(generateKey()).parseClaimsJws(token);
    }

    /**
     * 校验token,校验是否是本服务器的token
     * @param token token字符串
     * @return boolean
     */
    public static Boolean checkToken(String token) {
        return parseToken(token).getBody() != null;
    }

    /**
     * 根据sub判断token
     * @param token token字符串
     * @param sub 面向的用户
     * @return boolean
     */
    public static Boolean checkToken(String token,String sub){
        return parseToken(token).getBody().getSubject().equals(sub);
    }

}
