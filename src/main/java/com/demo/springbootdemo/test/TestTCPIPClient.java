package com.demo.springbootdemo.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

/**
 * TCP协议socket传输客户端
 *
 * @author: guan.kai
 * @date: 2019/11/22 16:46
 **/
public class TestTCPIPClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestTCPIPClient.class);

    //报文头长度
    private static final int LEN_LENGHT=8;
    //默认编码
    private static final String DEFULT_ENCODING="GBK";

    public static void main(String[] args) {
        Socket socket = null;
        try {
            socket = new Socket("127.0.0.1", 6969);
            OutputStream out = socket.getOutputStream();
            socket.setSoTimeout(60000);
            out.write(wrapMessagePackage(("<body_req><TranCode></TranCode></body_req>").getBytes(DEFULT_ENCODING)));
            out.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), DEFULT_ENCODING));
            StringBuilder builder = new StringBuilder();
            String msg = null;
            while ((msg = reader.readLine()) != null){
                System.out.println(msg);
                builder.append(msg);
            }
            LOGGER.info("=======客户端接收报文=======\n{}", builder.toString());
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            if (socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
        }
    }

    /**
     * 添加报文长度
     * @param msg
     * @return
     */
    private static byte[] wrapMessagePackage(byte[] msg){
        byte[] retMsg = null;
        try {
            //将使用系统默认字符集编码的数据转换成gbk编码
            msg = new String(msg,System.getProperty("file.encoding")).getBytes(DEFULT_ENCODING);

            retMsg = new byte[msg.length + LEN_LENGHT];
            String lenStr = String.valueOf(msg.length);
            lenStr = "0000000000" + lenStr;
            lenStr = lenStr.substring(lenStr.length() - LEN_LENGHT);
            System.arraycopy(lenStr.getBytes(), 0, retMsg, 0, LEN_LENGHT);
            System.arraycopy(msg, 0, retMsg, LEN_LENGHT, msg.length);
            LOGGER.info("=======客户端发送报文=======\n{}", new String(retMsg, DEFULT_ENCODING));
        } catch (Exception e){
            LOGGER.error(e.getMessage(), e);
        }
        return retMsg;
    }

}
