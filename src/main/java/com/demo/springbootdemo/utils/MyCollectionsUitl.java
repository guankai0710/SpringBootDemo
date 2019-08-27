package com.demo.springbootdemo.utils;

import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 自定义集合工具类
 *
 * @Author: guan.kai
 * @CreateTime: 2019/8/19 23:12
 * @Version: 0.0.1
 **/
public class MyCollectionsUitl {

    private static final Logger logger = LoggerFactory.getLogger(MyCollectionsUitl.class);

    /**
     * 截取list集合，根据截取大小返回多段list
     *
     * @Author: kai.guan
     * @CreateTime: 2019/8/19 23:55
     * @Param: [list, subSize]
     * @return: java.util.List<java.util.List<T>>
     **/
    public static <T> List<List<T>> subList(List<T> list, int subSize){
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

    public static <T> List<T> propertyToList(List<T> list, String property){
        List<T> resultList = new ArrayList<>();
        char[] cs = property.toCharArray();
        cs[0] -= 32;
        for (T t : list) {
            try {
                resultList.add((T) t.getClass().getMethod("get" + String.valueOf(cs)).invoke(t));
            } catch (NoSuchMethodException e) {
                logger.error(e.getMessage());
            } catch (IllegalAccessException ex) {
                logger.error(ex.getMessage());
            } catch (InvocationTargetException exx) {
                logger.error(exx.getMessage());
            }
        }
        return resultList;
    }
}
