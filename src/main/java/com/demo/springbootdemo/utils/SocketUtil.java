package com.demo.springbootdemo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.UUID;

/**
 * socket连接工具类
 *
 * @author: guan.kai
 * @date: 2019/9/16 11:06
 **/
public class SocketUtil {

    private static final Logger logger = LoggerFactory.getLogger(SocketUtil.class);

    /**
     * socket服务实例
     */
    private static Socket socket;

    /**
     *
     */
    private static final String HEAD_FORMAT_TEN = "%08d";

    /**
     * 默认GBK编码
     */
    private static final String ENCODING_GBK = "GBK";

    /**
     *
     */
    private static final Integer BUFFER_SIZE = 10240;

    /**
     * 默认服务超时时间
     */
    private static final Integer TIME_OUT = 30000;

    /**
     * 推送消息并返回结果
     *
     * @param sendContent 发送内容（xml格式字符串）
     * @param socketIp 目标服务器地址
     * @param port 目标服务端口
     * @param timeOut 服务超时时间
     * @param headFormat
     * @param encoding 内容编码
     * @param bufferSize
     * @return 返回内容（xml格式字符串）
     */
    public static String sendMessage(String sendContent, String socketIp, Integer port, Integer timeOut, String headFormat, String encoding, Integer bufferSize){
        DataOutputStream dos = null;
        DataInputStream dis = null;
        String result = null;
        String seqNo = UUID.randomUUID().toString();

        try {
            if (socket == null){
                call(socketIp,port);
            }
            setSoTimeOut(timeOut);
            logger.info("初始化SCOKET服务，目标服务地址：{}，端口：{}。发送内容：{}。序号为：{}。",socketIp,port,sendContent,seqNo);
            dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            writerByteData(dos,sendContent,headFormat,encoding);
            dis = new DataInputStream(new BufferedInputStream(socket.getInputStream(),bufferSize));
            result = readByteData(dis,encoding,bufferSize);
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
        } finally {
            if (dis != null){
                try {
                    dis.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(),e);
                }
            }
            if (dos != null){
                try {
                    dos.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(),e);
                }
            }
            close();
        }
        logger.info("目标服务地址：{}，端口：{}。返回内容：{}。序号为：{}。",socketIp,port,result,seqNo);
        return result;
    }

    public static String sendMessage(String sendContent, String socketIp, Integer port){
        return sendMessage(sendContent,socketIp,port,TIME_OUT,HEAD_FORMAT_TEN,ENCODING_GBK,BUFFER_SIZE);
    }

    public static String sendMessage(String sendContent, String socketIp,  Integer port, Integer timeOut){
        return sendMessage(sendContent,socketIp,port,timeOut,HEAD_FORMAT_TEN,ENCODING_GBK,BUFFER_SIZE);
    }

    public static String sendMessage(String sendContent, String socketIp,  Integer port, String headFormat){
        return sendMessage(sendContent,socketIp,port,TIME_OUT,headFormat,ENCODING_GBK,BUFFER_SIZE);
    }

    public static String sendMessage(String sendContent, String socketIp,  Integer port, String headFormat, String encoding){
        return sendMessage(sendContent,socketIp,port,TIME_OUT,headFormat,encoding,BUFFER_SIZE);
    }


    /**
     * 创建socket连接
     *
     * @param socketIp 目标服务器地址
     * @param port 目标服务端口
     */
    private static void call(String socketIp, Integer port){
        try {
            socket = new Socket(socketIp,port);
        } catch (IOException e) {
            logger.error("服务器连接错误，地址：{}，端口：{}",socketIp,port);
        }
    }

    /**
     * 设置服务器超时时间
     *
     * @param sec 毫秒
     */
    private static void setSoTimeOut(Integer sec){
        try {
            socket.setSoTimeout(sec);
        } catch (SocketException e) {
            logger.error("服务器连接超时！");
        }
    }

    /**
     * 关闭socket连接
     */
    private static void close(){
        try {
            if (socket != null){
                socket.close();
            }
        } catch (IOException e) {
            logger.error("服务器连接异常！");
        }
    }

    /**
     * 发送字节数据
     *
     * @param out
     * @param message
     * @param headFormat
     * @param encoding
     */
    private static void writerByteData(OutputStream out, String message, String headFormat, String encoding){
        try {
            long len = message.getBytes(encoding).length;
            String head = String.format(headFormat, len);
            out.write(String.format("%s%s",head,message).getBytes(encoding));
            out.flush();
        } catch (IOException e) {
            logger.error("消息发送异常！");
        }
    }

    /**
     * 接收字节数据
     *
     * @param in
     * @param encoding
     * @param size
     * @return
     */
    private static String readByteData(InputStream in, String encoding, Integer size){
        StringBuffer buffer = new StringBuffer();
        try {
            byte[] bytes = new byte[size];
            int readLength = 0;
            while ((readLength = in.read(bytes,0,size)) != -1){
                buffer.append(new String(bytes,0,readLength,encoding));
            }
        } catch (IOException e) {
            logger.error("消息接收异常！");
        }
        return buffer.toString();
    }


}
