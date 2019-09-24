package com.demo.springbootdemo.utils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.basic.NullConverter;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.WeakHashMap;

/**
 * Xstream工具类
 * 用于处理xml与bean之间的转换
 *
 * @author: guan.kai
 * @date: 2019/9/16 9:39
 **/
public class XstreamUtil {

    private static Logger logger = LoggerFactory.getLogger(XstreamUtil.class);

    /**
     * 默认编码
     */
    private static final String DEFULT_ENCODING = "UTF-8";

    /**
     * 用于存储不同类型的Xstream，避免频繁创建
     */
    private static Map<Class<?>, XStream> xStreamMap = new WeakHashMap<>();

    /**
     * 获取Xstream
     *
     * @param classType
     * @param encoding
     * @return
     */
    public static XStream getXstream(Class<?> classType, String encoding){
        if (xStreamMap.containsKey(classType)){
            return xStreamMap.get(classType);
        }
        XStream xStream = new XStream(new DomDriver(encoding));
        logger.info("create xstream by {}",classType.getName());
        xStream.processAnnotations(classType);
        xStreamMap.put(classType,xStream);
        return xStream;
    }

    /**
     * 获取Xstream (使用默认编码utf-8)
     *
     * @param classType
     * @return
     */
    public static XStream getXstream(Class<?> classType){
        return getXstream(classType, DEFULT_ENCODING);
    }

    /**
     * 通过Xsteam对象将xml字符串转换成bean对象
     *
     * @param clssType
     * @param xmlString
     * @param encoding
     * @return
     */
    public static Object xmlToBean(Class<?> clssType, String xmlString, String encoding){
        return getXstream(clssType,encoding).fromXML(xmlString);
    }

    /**
     * 通过Xsteam对象将xml字符串转换成bean对象
     *
     * @param clssType
     * @param xmlString
     * @return
     */
    public static Object xmlToBean(Class<?> clssType, String xmlString){
        return getXstream(clssType).fromXML(xmlString);
    }

    /**
     * 通过Xsteam对象将bean对象转换成xml字符串
     *
     * @param clssType
     * @param bean
     * @param encoding
     * @return
     */
    public static String beanToXml(Class<?> clssType, Object bean, String encoding){
        return getXstream(clssType,encoding).toXML(bean);
    }

    /**
     * 通过Xsteam对象将bean对象转换成xml字符串
     *
     * @param clssType
     * @param bean
     * @return
     */
    public static String beanToXml(Class<?> clssType, Object bean){
        return getXstream(clssType).toXML(bean);
    }

    /**
     * 通过Xsteam对象将bean对象转换成xml字符串转换空字符串
     *
     * @param classType
     * @param bean
     * @param encoding
     * @return
     */
    public static String beanToXmlCounverNull(Class<?> classType, Object bean, String encoding){
        XStream xstream = getXstream(classType,encoding);
        xstream.registerConverter(new NullConverter());
        return xstream.toXML(bean);
    }

    /**
     * 通过Xsteam对象将bean对象转换成xml字符串转换空字符串
     *
     * @param classType
     * @param bean
     * @return
     */
    public static String beanToXmlCounverNull(Class<?> classType, Object bean){
        XStream xstream = getXstream(classType);
        xstream.registerConverter(new NullConverter());
        return xstream.toXML(bean);
    }


}
