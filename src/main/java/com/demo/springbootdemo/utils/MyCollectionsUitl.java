package com.demo.springbootdemo.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * 自定义集合工具类
 *
 * @Author: guan.kai
 * @CreateTime: 2019/8/19 23:12
 * @Version: 0.0.1
 **/
public class MyCollectionsUitl {

    private MyCollectionsUitl() {
    }

    private static final Logger logger = LoggerFactory.getLogger(MyCollectionsUitl.class);

    private static final String MSG = "参数不能为空！";
    private static final String COMMA = ",";

    /**
     * 抽取某个属性，返回list
     *
     * @Author: guan.kai
     * @CreateTime: 2019/8/28 14:24
     * @Param: [collection, propertyName：属性名]
     * @Return: java.util.List
     **/
    public static List extractToList(Collection collection, String propertyName){
        if (collection != null && StringUtils.isNotBlank(propertyName)){
            List list = new ArrayList(collection.size());
            //将属性名字的首字母大写
            propertyName = propertyName.replaceFirst(propertyName.substring(0, 1), propertyName.substring(0, 1).toUpperCase());
            for (Object obj : collection) {
                try {
                    Object invoke = obj.getClass().getMethod("get" + propertyName).invoke(obj);
                    list.add(invoke);
                } catch (NoSuchMethodException|IllegalAccessException|InvocationTargetException e) {
                    logger.error(e.getMessage());
                }
            }
            return list;
        }
        throw new NullPointerException(MSG);
    }

    /**
     * 抽取某个属性，以分隔符拼接成字符串返回,cutFlag为null或者空字符串时，默认使用","逗号分隔
     *
     * @Author: guan.kai
     * @CreateTime: 2019/8/28 14:25
     * @Param: [collection, propertyName：属性名, cutFlag：分隔符]
     * @Return: java.lang.String
     **/
    public static String extractToString(Collection collection, String propertyName, String cutFlag){
        if (collection != null && StringUtils.isNotBlank(propertyName)){
            StringBuilder str = new StringBuilder();
            //将属性名字的首字母大写
            propertyName = propertyName.replaceFirst(propertyName.substring(0, 1), propertyName.substring(0, 1).toUpperCase());
            for (Object obj : collection) {
                try {
                    String invoke = (String)obj.getClass().getMethod("get" + propertyName).invoke(obj);
                    if (StringUtils.isBlank(cutFlag)){
                        cutFlag = COMMA;
                    }
                    str.append(cutFlag).append(invoke);
                } catch (NoSuchMethodException|IllegalAccessException|InvocationTargetException e) {
                    logger.error(e.getMessage());
                }
            }
            return str.toString().substring(cutFlag.length());
        }
        throw new NullPointerException(MSG);
    }

    /**
     * 抽取某个属性，返回一个 以该属性为key、对应对象为value的map集合
     *
     * @Author: guan.kai
     * @CreateTime: 2019/8/28 14:26
     * @Param: [collection, propertyName]
     * @Return: java.util.Map
     **/
    public static Map extractToMap(Collection collection, String propertyName){
        if (collection != null && StringUtils.isNotBlank(propertyName)){
            Map map = new HashMap(collection.size());
            //将属性名字的首字母大写
            propertyName = propertyName.replaceFirst(propertyName.substring(0, 1), propertyName.substring(0, 1).toUpperCase());
            for (Object obj : collection) {
                try {
                    Object invoke = obj.getClass().getMethod("get" + propertyName).invoke(obj);
                    map.put(invoke,obj);
                } catch (IllegalAccessException|InvocationTargetException|NoSuchMethodException e) {
                    logger.error(e.getMessage());
                }
            }
            return map;
        }
        throw new NullPointerException(MSG);
    }

    /**
     * 根据属性抽取，返回map
     *
     * @Author: guan.kai
     * @CreateTime: 2019/8/28 14:28
     * @Param: [collection, keyPropertyName, valuePropertyName]
     * @Return: java.util.Map
     **/
    public static Map extractToMap(Collection collection, String keyPropertyName, String valuePropertyName){
        if (collection != null && StringUtils.isNotBlank(keyPropertyName) && StringUtils.isNotBlank(valuePropertyName)){
            Map map = new HashMap(collection.size());
            //将属性名字的首字母大写
            keyPropertyName = keyPropertyName.replaceFirst(keyPropertyName.substring(0, 1), keyPropertyName.substring(0, 1).toUpperCase());
            valuePropertyName = valuePropertyName.replaceFirst(valuePropertyName.substring(0, 1), valuePropertyName.substring(0, 1).toUpperCase());
            for (Object obj : collection) {
                try {
                    Object key = obj.getClass().getMethod("get" + keyPropertyName).invoke(obj);
                    Object value = obj.getClass().getMethod("get" + valuePropertyName).invoke(obj);
                    map.put(key,value);
                } catch (IllegalAccessException|InvocationTargetException|NoSuchMethodException e) {
                    logger.error(e.getMessage());
                }
            }
            return map;
        }
        throw new NullPointerException(MSG);
    }

    /**
     * 截取list集合，根据截取大小返回多段list
     *
     * @Author: kai.guan
     * @CreateTime: 2019/8/19 23:55
     * @Param: [list, subSize]
     * @return: java.util.List<java.util.List<T>>
     **/
    public static <T> List<List<T>> subList(List<T> list, int subSize){
        if (list != null){
            List<List<T>> resultList = new ArrayList<>();
            int size = list.size();
            if (size < subSize) {
                resultList.add(list);
            } else {
                int count = (size + subSize - 1) / subSize;
                for (int i = 0; i < count; i++) {
                    resultList.add(list.subList(i * subSize, ((i + 1) * subSize > size ? size : subSize * (i + 1))));
                }
            }
            return resultList;
        }
        throw new NullPointerException(MSG);
    }

}
